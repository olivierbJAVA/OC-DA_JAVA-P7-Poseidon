package com.nnk.springboot.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Class managing the configuration of the security of the application.
 * This Class extends WebSecurityConfigurerAdapter and uses Spring Security.
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * Method managing the configuration for the Authorization in the application.
     *
     * @param http The HttpSecurity object used to manage Authorization in the application
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Users having either a USER or ADMIN role are authorized to access and manage (Create, Read, Update, Delete) financial entities (BidList, CurvePoint, Rating, RuleName and Trade)
                .antMatchers("/", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").hasAnyAuthority("USER", "ADMIN")
                // Only users having a ADMIN role are authorized to access and manage (Create, Read, Update, Delete) Users
                .antMatchers("/user/**", "/admin/home").hasAuthority("ADMIN")
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/bidList/list")
                .and()
                .exceptionHandling().accessDeniedPage("/errorAccessDenied");
    }

    /**
     * Method managing the configuration for the Authentication to the application.
     *
     * @param auth The AuthenticationManagerBuilder object used to manage Authentication to the application
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                // SLQ request to get username and password of the user from the database
                .usersByUsernameQuery("SELECT username AS principal, password AS credentials, true FROM users WHERE username = ?")
                // SLQ request to get username and role of the user from the database
                .authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM users WHERE username = ?");
    }

    /**
     * Method returning a password encoder using the BCrypt algorithm.
     * This algorithm is used to hash passwords before saving them in the database.
     *
     * @return The BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
