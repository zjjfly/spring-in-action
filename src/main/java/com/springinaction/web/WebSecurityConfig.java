package com.springinaction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by zjjfly on 2017/1/13.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用内存配置用户
//        auth.inMemoryAuthentication()
//                .withUser("jjzi").password("12345").roles("USER").and()
//                .withUser("admin").password("admin").roles("USER","ADMIN");
        //使用数据库存储用户
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true from Spitter where username=?")//自定义查询用户名，密码，是否启用的sql语句
                .authoritiesByUsernameQuery("select username,'ROLE_SPITTER' from Spitter where username=?")//自定义查询用户权限的sql语句
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().loginPage("/login").successForwardUrl("/home").and()
                .logout().logoutSuccessUrl("/").and()
                .rememberMe().key("spittrKey").tokenValiditySeconds(60).and()
                .authorizeRequests().antMatchers("/spitter/me").access("hasRole('ROLE_SPITTER') and hasIpAddress('127.0.0.1')")
                .antMatchers("/spitter/register").permitAll()
                .antMatchers("/spitter/**").authenticated()
                .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
                .antMatchers( "/home").hasRole("SPITTER")
                .anyRequest().permitAll()
                .and()
                .requiresChannel().antMatchers("/spitter/register").requiresSecure()
                .antMatchers(HttpMethod.POST, "/spittles").requiresSecure()
                .antMatchers("/").requiresInsecure();
    }
}

