package com.blogpeliculas.security;

import com.blogpeliculas.persistencia.enums.ERol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                    session.maximumSessions(1);
                })
                .authorizeHttpRequests(http -> {
                    http.requestMatchers("/css/*", "/js/*", "/auth/registro").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/","/home/*").authenticated();
                    http.requestMatchers(HttpMethod.DELETE, "/*").hasAnyRole(ERol.MODERADOR.name(), ERol.ADMIN.name(), ERol.DEVELOPER.name());
                    http.requestMatchers("/admin/*").hasRole(ERol.ADMIN.name());
                    http.requestMatchers(HttpMethod.DELETE, "/admin/**").fullyAuthenticated();
                    http.anyRequest().authenticated();
                })
//                .exceptionHandling( excepcionHandling ->
//                        excepcionHandling.accessDeniedPage("/denegado"))
                .formLogin(form -> {
                    form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .successHandler(new AuthenticationSuccessHandler())
                        .permitAll();})
//                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret").tokenValiditySeconds(604800)) //86400 sec is one day
                .logout((logout) -> logout
                        .logoutSuccessUrl("/auth/login?logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return new Argon2PasswordEncoder();
    }

}
