package com.kapcb.ccc.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * <a>Title: RedisAuthenticationCodeServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 * 将授权码保存到Redis中, 确保认证服务器集群的一致性
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/8 22:41
 */
@Slf4j
@Service
public class RedisAuthenticationCodeServiceImpl extends RandomValueAuthorizationCodeServices {

    private static final String AUTH_CODE_KEY = "auth_code";

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisAuthenticationCodeServiceImpl(@Autowired RedisConnectionFactory redisConnectionFactory) {
        Assert.notNull(redisConnectionFactory, "redis connection factory can not be null");
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        RedisConnection redisConnection = getRedisConnection();
        try {
            Boolean result = redisConnection.hSet(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8), SerializationUtils.serialize(oAuth2Authentication));
            log.info(result ? "save oauth2 authentication to redis server success!" : "save oauth2 authentication to redis server fail!");
        } catch (Exception e) {
            log.error("save oauth2 authentication to redis server fail! error message is : " + e.getMessage());
        } finally {
            redisConnection.close();
        }
    }

    @Nullable
    @Override
    protected OAuth2Authentication remove(@NonNull String code) {
        RedisConnection redisConnection = getRedisConnection();
        try {
            byte[] bytes = redisConnection.hGet(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            if (bytes == null || ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            OAuth2Authentication oAuth2Authentication = SerializationUtils.deserialize(bytes);
            if (oAuth2Authentication != null) {
                redisConnection.hDel(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            }
            return oAuth2Authentication;
        } catch (Exception e) {
            log.error("remove oauth2 authentication fail, error message is : " + e.getMessage());
            return null;
        } finally {
            redisConnection.close();
        }
    }

    private RedisConnection getRedisConnection() {
        return redisConnectionFactory.getConnection();
    }
}
