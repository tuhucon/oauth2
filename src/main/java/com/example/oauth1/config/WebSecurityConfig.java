package com.example.oauth1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = false)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/api/basic", "/api/me").authenticated()
//            .antMatchers("/filter").permitAll()
//            .and().requestMatchers().antMatchers("/filter", "/api/**");
//        super.configure(http);
        http.authorizeRequests().anyRequest().authenticated()
            .and().requestMatchers().antMatchers("/oauth/authorize")
            .and().httpBasic();
    }
}
