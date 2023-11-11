package com.example.multiplace.config;


import com.example.multiplace.model.enums.UserRoleEnum;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class SecurityConfig {


    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean

    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.

                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "users/register", "/users/login-error")
                        .permitAll()
                        .requestMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/pages/company").hasRole(UserRoleEnum.COMPANY.name())
                        .anyRequest().authenticated())
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/users/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);

                }).rememberMe(rememberMe -> {
                    rememberMe
                            .key("baubau")
                            .tokenValiditySeconds(5555)
                            .userDetailsService(new AppUserDetailsService(userRepository));
                });
        return httpSecurity.build();
    }


    //    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/public/**").permitAll()
//                .requestMatchers("/private/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .csrf().disable();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}