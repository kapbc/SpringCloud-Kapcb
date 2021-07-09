package com.kapcb.ccc.auth.configuration;

import com.kapcb.ccc.auth.properties.AuthProperties;
import com.kapcb.ccc.auth.service.impl.RedisAuthenticationCodeServiceImpl;
import com.kapcb.ccc.auth.service.impl.RedisClientDetailServiceImpl;
import com.kapcb.ccc.auth.translator.AuthWebExceptionTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * <a>Title: KapcbAuthorizationServerConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/6 22:43
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class KapcbAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthProperties authProperties;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final RedisClientDetailServiceImpl redisClientDetailService;
    private final AuthWebExceptionTranslator authWebExceptionTranslator;
    private final RedisAuthenticationCodeServiceImpl redisAuthenticationCodeService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(redisClientDetailService);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(redisAuthenticationCodeService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(authWebExceptionTranslator);
        if (authProperties.getEnableJwt()) {
            log.info("enable jwt access token converter");
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
        }
    }

    @Bean
    public TokenStore tokenStore() {
        if (authProperties.getEnableJwt()) {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }
        return null;
    }

    @Bean
    @ConditionalOnProperty(prefix = "kapcb.auth.enableJwt", havingValue = "true")
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        log.info("begin to auto configure jwt access token converter");
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(userDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter);
        jwtAccessTokenConverter.setSigningKey(authProperties.getJwtAccessKey());
        return jwtAccessTokenConverter;
    }
}
