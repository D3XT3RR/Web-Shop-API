package com.zarlok.webshop.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder  auth, DataSource dataSource, PasswordEncoder passwordEncoder) throws Exception{
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authManager =
                auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                        .passwordEncoder(passwordEncoder);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/public/css/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers("//categories*").hasRole("USER");
        httpSecurity.authorizeRequests().antMatchers("/login*").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.formLogin()
                //.loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/home", true)
                .and().logout().deleteCookies("JSESSIONID");

          httpSecurity.httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return r;
    }
}
