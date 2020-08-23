package com.nnk.springboot.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
                .antMatchers("/", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**","/trade/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/user/**", "/admin/home").hasAuthority("ADMIN")
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/bidList/list")
                .and()
            .exceptionHandling().accessDeniedPage("/error403");
    }

    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
          auth.jdbcAuthentication()
            .passwordEncoder(passwordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT username AS principal, password AS credentials, true FROM users WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM users WHERE username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
