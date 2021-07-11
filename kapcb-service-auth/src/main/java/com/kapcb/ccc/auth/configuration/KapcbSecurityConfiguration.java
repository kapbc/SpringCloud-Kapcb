package com.kapcb.ccc.auth.configuration;

import com.kapcb.ccc.auth.filter.VerificationCodeFilter;
import com.kapcb.ccc.auth.handler.WebAuthenticationFailureHandler;
import com.kapcb.ccc.auth.handler.WebAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <a>Title: KapcbSecurityConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 0:40
 */
@Order(2)
@EnableWebSecurity
@RequiredArgsConstructor
public class KapcbSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final VerificationCodeFilter verificationCodeFilter;
    private final WebAuthenticationFailureHandler webAuthenticationFailureHandler;
    private final WebAuthenticationSuccessHandler webAuthenticationSuccessHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers("oauth_all", "/login")
                .and()
                .authorizeRequests()
                .antMatchers("oauth_all").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("login")
                .successHandler(webAuthenticationSuccessHandler)
                .failureHandler(webAuthenticationFailureHandler)
                .permitAll()
                .and().csrf().disable()
                .httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}