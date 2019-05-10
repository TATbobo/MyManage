package com.tucker.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("UserDetailService1")
    @Autowired
    private UserDetailsService userDetailsService;

    /*@Autowired
    public void confiureGlobal(AuthenticationManagerBuilder auth) throws Exception{

    }*/

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return  authProvider;
    }

    @Bean
    public PasswordEncoder encoder(){
        return  new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //定制授权规则
        /*http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/dashboard").hasRole("Customer");*/
        //开启登陆界面
        http.formLogin().usernameParameter("Username").passwordParameter("Password").loginPage("/login").failureUrl("/login/error")
                .and()
                .authorizeRequests()
                .antMatchers("/login","/assets/**","/register","/login/error").permitAll()
                .anyRequest()
                .authenticated();

        http.logout().logoutSuccessUrl("/");
        http.rememberMe().rememberMeParameter("remember-me");
    }

}
