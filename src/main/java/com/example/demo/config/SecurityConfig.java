package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //set the target out-of security
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Do not apply security
            web.ignoring()
                    .antMatchers("/webjars/**")
                    .antMatchers("/css/**")
                    .antMatchers("/js/**")
                    .antMatchers("/h2/**");
    }
    //various security setting

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll() //direct link OK
                .antMatchers("/user/signup").permitAll() //direct link OK
                .anyRequest().authenticated(); //otherwise direct link is NG

        http
            .formLogin()
                .loginProcessingUrl("/login") //login process path
                .loginPage("/login") //specify the login page
                .failureUrl("/login?error") //when login failed
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/list",true);

        //disable CSRF (temporary)
        http.csrf().disable();
    }

    /**Authentication setting**/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //In-memory authentication
//        auth
//            .inMemoryAuthentication()
//                .withUser("user") //add user
//                    .password(encoder.encode("user"))
//                    .roles("GENERAL")
//                .and()
//                .withUser("admin") //add admin
//                    .password(encoder.encode("admin"))
//                    .roles("ADMIN");
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
