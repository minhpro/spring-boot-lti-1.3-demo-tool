package com.example.demoltitool.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import uk.ac.ox.ctl.lti13.Lti13Configurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/resources/**", "/favicon.ico")
                .permitAll()
        ;
        Lti13Configurer lti13Configurer = new Lti13Configurer();
        http.apply(lti13Configurer);
    }

}
