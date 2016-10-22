package com.mitchellbosecke.seniorcommander.web;

import com.mitchellbosecke.pebble.extension.Extension;
import com.mitchellbosecke.seniorcommander.web.ui.PebbleExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public Extension pebbleExtension(){
        return new PebbleExtension();
    }

    public static void main(String[] args) {
        SpringApplication.run(SeniorCommanderWebApplication.class, args);
    }
}
