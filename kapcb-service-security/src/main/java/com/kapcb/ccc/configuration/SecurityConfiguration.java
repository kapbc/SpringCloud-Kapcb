package com.kapcb.ccc.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <a>Title: SecurityConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/27 15:36
 */
@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Spring Security: 中如果需要自定义配置, 基本上都是继承 WebSecurityConfigurerAdapter 来实现。
     * configure: 方法中是一个链式调用, 也可以不使用链式配置, 每一个属性配置完毕后再从 http. 重新开始写起。
     * authorizeRequests(): 方法表示开启权限配置。
     * anyRequest().authenticated(): 表示所有的请求都要认证之后才能进行访问。
     * and(): 方法会返回 HttpSecurityBuilder 对象的一个子类 HttpSecurity, 所以 and()方法相当于又回到 HttpSecurity 实例
     * 如果不希望链式调用, 可以在 .anyRequest().authenticated() 方法之后直接用分号(;)结束, 然后再次使用 http 进行后续配置。
     * formLogin(): 表示开启表单登录配置。
     * loginPage(): 表示用来配置登录页面的地址。
     * loginProcessingUrl(): 表示配置登录接口地址。
     * defaultSuccessUrl(): 表示登录成功之后的跳转地址。
     * failureUrl(): 表示登录失败之后的跳转地址。
     * usernameParameter(): 表示登录密码的参数名称。
     * passwordParameter(): 表示登陆密码参数名称。
     * permitAll(): 表示与登录相关的页面与接口不做拦截。
     * csrf().disable(): 表示关闭CSRF防御功能, 为了调试方便。
     * 注意: loginProcessingUrl、usernameParameter、passwordParameter需要和login.html中登录表单的配置保持一致。
     *
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.html")
                .failureUrl("/login.html")
                .usernameParameter("username-login")
                .passwordParameter("password-login")
                .permitAll()
                .and()
                .csrf().disable();
    }
}