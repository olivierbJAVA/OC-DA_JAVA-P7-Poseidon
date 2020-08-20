package com.nnk.springboot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             //.csrf().disable()
             .authorizeRequests()
                .antMatchers("/user/**", "/admin/home").hasRole("ADMIN")
                .antMatchers("/", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**","/trade/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //.defaultSuccessUrl("/index.html")
                .and()
            .exceptionHandling().accessDeniedPage("/403");
    }

    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
                //.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");
    }
/*
protected void configure (AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
    auth.jdbcAuthentication()
        .passwordEncoder(passwordEncoder())
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT username AS principal, password AS credentials, true FROM users WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM users WHERE username = ?");
        //.usersByUsernameQuery("SELECT username, password, true FROM users WHERE username = ?")
        //.authoritiesByUsernameQuery("SELECT username, role FROM users WHERE username = ?");
}
*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
