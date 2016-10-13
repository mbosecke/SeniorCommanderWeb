package com.mitchellbosecke.seniorcommander.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeniorCommanderWebApplication extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //@formatter:off
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/css/**", "/privacy", "/terms-of-service").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        //@formatter:on
    }

    public static void main(String[] args) {
        SpringApplication.run(SeniorCommanderWebApplication.class, args);
    }
}
