package com.glsid.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by X-MART on 5/10/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal, roles as role from users_roles where username=?")
        .rolePrefix("ROLE_").
        passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/consulterSociete","/societe","/ordres","/consulterOrdres")
                .hasRole("USER");
        http.authorizeRequests().antMatchers("/saveSociete","/saveOrdre").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
