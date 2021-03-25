package com.simple.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/post/{id}"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable().authorizeRequests().antMatchers(AUTH_LIST).permitAll()
                    .anyRequest().authenticated().and().formLogin().loginPage("/login")
                    .usernameParameter("email").permitAll().and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication().withUser("willyanf17@hotmail.com").password("{noop}123456").roles("ADMIN");

    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/bootstrap/**", "/style/**");
    }


}