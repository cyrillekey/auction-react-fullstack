package com.bidding.auction;
/**
 * disable security for springboot
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter{
    @Override
    /**
     * method to disable security to enable local testing
     */
    protected void configure(HttpSecurity http) throws Exception {    
        http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll();
            http. headers().frameOptions().disable();
        }
}
