package com.kapcb.ccc.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kapcb.ccc.auth.constants.AuthConstant;
import kapcb.framework.web.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * <a>Title: RedisClientDetailServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/8 22:26
 */
@Slf4j
@Service
public class RedisClientDetailServiceImpl extends JdbcClientDetailsService {

    private final RedisService redisService;

    @Autowired
    public RedisClientDetailServiceImpl(DataSource dataSource, RedisService redisService) {
        super(dataSource);
        this.redisService = redisService;
    }

    /**
     * 获取 client 信息
     *
     * @param clientId String
     * @return ClientDetails
     * @throws InvalidClientException InvalidClientException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        log.info("the client id is: " + clientId);
        String jsonString = (String) redisService.getHash(AuthConstant.CACHE_CLIENT_KEY, clientId);
        log.info("the json string is : " + jsonString);
        return StringUtils.isNotBlank(jsonString) ? JSONObject.parseObject(jsonString, BaseClientDetails.class) : cacheAndGet(clientId);
    }

    /**
     * 缓存 client detail 并返回
     *
     * @param clientId String
     * @return ClientDetails
     */
    private ClientDetails cacheAndGet(String clientId) {
        return null;
    }
}
