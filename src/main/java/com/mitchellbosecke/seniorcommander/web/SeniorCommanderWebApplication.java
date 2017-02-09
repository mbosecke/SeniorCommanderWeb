package com.mitchellbosecke.seniorcommander.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories("com.mitchellbosecke")
@ComponentScan("com.mitchellbosecke")
@EntityScan("com.mitchellbosecke")
public class SeniorCommanderWebApplication extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .antMatcher("/**")
            .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/privacy", "/terms-of-service")
                .permitAll()
            .anyRequest()
                .fullyAuthenticated()
            .and()
                .csrf().disable()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home");
        //@formatter:on
    }

    public static void main(String[] args) {
        SpringApplication.run(SeniorCommanderWebApplication.class, args);
    }
}
