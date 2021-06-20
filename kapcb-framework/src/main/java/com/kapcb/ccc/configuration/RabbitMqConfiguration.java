package com.kapcb.ccc.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.concurrent.SettableListenableFuture;

/**
 * <a>Title: RabbitMqConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RabbitMq Configuration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:13
 */
@Slf4j
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 生产者推送消息的消息确认调用回调函数已经完毕
     * <p>
     * 推送消息存在四种情况：
     * ①消息推送到server，但是在server里找不到交换机
     * ②消息推送到server，找到交换机了，但是没找到队列
     * ③消息推送到sever，交换机和队列啥都没找到
     * ④消息推送成功
     *
     * @param connectionFactory ConnectionFactory
     * @return RabbitTemplate
     */
    @Bean(value = "rabbitTemplate")
    @Scope(value = "prototype")
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        /**
         * 设置开启Mandatory才能触发回调函数，无论推送消息结果如何都强制调用回调函数
         * 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
         */
        rabbitTemplate.setMandatory(true);

        /**
         * producer ---> exchange 消息发送确认回调方法
         * 如果消息没有到exchange,则confirm回调,ack=false
         * 如果消息到达exchange,则confirm回调,ack=true
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("RabbitConfirmCallback        correlationData : " + correlationData);
            log.info("RabbitConfirmCallback        ack : " + ack);
            log.info("RabbitConfirmCallback        cause : " + cause);
            StringBuilder alertMessage = new StringBuilder();
            if (ack) {
                if (correlationData != null) {
                    String id = correlationData.getId();
                    SettableListenableFuture<CorrelationData.Confirm> future = correlationData.getFuture();
                    Message returnedMessage = correlationData.getReturnedMessage();
                    Class<? extends CorrelationData> aClass = correlationData.getClass();
                    log.info("the correlationData's id is : " + id);
                    log.info("the correlationData's future is : " + future);
                    log.info("the correlationData's returnedMessage is : " + returnedMessage);
                    log.info("the correlationData's aClass is : " + aClass);
                    log.info("the rabbit exchange have received the message!");
                }
            } else {
                /**
                 * 消费者消息接收失败
                 * 失败进行具体的后续处理：重试 或者补偿等手段
                 */
                log.info("send message to rabbitmq exchange error!");
                alertMessage.append("send message to exchange fail, the reason is : ").append(cause);
                log.info("the producer send message to exchange ,the confirm callback message is : " + alertMessage.toString());
            }
        });

        /**
         * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
         * 需要注意的是：该方法调用后，MsgSendConfirmCallBack中的confirm方法也会被调用，且ack = true
         * 确保消息成功发送 confirm 模式 如果失败则可以按照自己逻辑处理保存到数据库失败发送表 可后续做补偿
         * 确保消息成功消费 ack 手动应答  失败时按业务 选择从新投递或者丢弃
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("RabbitReturnCallback        message : " + message);
            log.info("RabbitReturnCallback        replyCode : " + replyCode);
            log.info("RabbitReturnCallback        replyText : " + replyText);
            log.info("RabbitReturnCallback        exchange : " + exchange);
            log.info("RabbitReturnCallback        routingKey : " + routingKey);
            log.info("the message consumer haven't received the message!");
        });

        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
