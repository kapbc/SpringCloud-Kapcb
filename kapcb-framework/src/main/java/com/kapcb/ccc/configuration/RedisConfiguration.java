package com.kapcb.ccc.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.kapcb.ccc.helper.BloomFilterHelper;
import com.kapcb.ccc.auth.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <a>Title: RedisConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Redis Configuration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 0:37
 */
@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean(value = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisService")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        log.info("begin to create redisTemplate and load into Spring Application Context...");
        // 关闭共享连接, 用于动态切换Redis数据库
        lettuceConnectionFactory.setShareNativeConnection(false);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        ObjectMapper objectMapper = new ObjectMapper();

        BasicPolymorphicTypeValidator polymorphicTypeValidator = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();

        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        // 序列化时将对象全类名一起保存下来
        objectMapper.activateDefaultTyping(polymorphicTypeValidator, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.registerModule(new Jdk8Module());
        // 时间Module
        objectMapper.registerModule(new JavaTimeModule());
        // 时间序列化成时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(value = RedisTemplate.class)
    public RedisService redisService(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        return new RedisService(redisTemplate);
    }

    /**
     * init bloomFilter
     *
     * @return BloomFilterHelper {@link BloomFilterHelper}
     */
    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public BloomFilterHelper<String> initBloomFilter() {
        return new BloomFilterHelper<>((Funnel<String>) (from, init) -> init.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8), 1000000, 0.01);
    }
}
