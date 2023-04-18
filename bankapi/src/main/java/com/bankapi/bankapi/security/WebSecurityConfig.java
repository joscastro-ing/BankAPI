package com.bankapi.bankapi.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer{

    private final UserDetailsService userDetailsService;
    private final JwtAthorizationFilter jwtAthorizationFilter;
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        return http.cors()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user").anonymous()
        .antMatchers(HttpMethod.POST, "/user/admins").anonymous()
        .antMatchers(HttpMethod.POST, "/account").anonymous()
        .antMatchers(HttpMethod.POST, "/login").anonymous()
        .antMatchers(HttpMethod.GET, "/type/{type}").anonymous()
        .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/account").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/type/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/type/**").hasRole("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(jwtAthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
   
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
 

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncodere())
        .and()
        .build();
    }

    @Bean
    PasswordEncoder passwordEncodere(){
        return new BCryptPasswordEncoder();
    }

}
