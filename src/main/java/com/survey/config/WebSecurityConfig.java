package com.survey.config;

import com.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/", "/restore_password", "/about").permitAll()
                    .antMatchers("/profile", "doctor/profile").hasRole("USER")
                    .antMatchers("/test", "/test/**").hasAnyRole("PATIENT", "DOCTOR")
                    .antMatchers("/patients", "patient/profile").hasRole("DOCTOR")
                    .antMatchers("/statistic").hasRole("ANALYTIC")
                    .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .rememberMe().tokenValiditySeconds(86400)
                    .userDetailsService(userService);
    }
}
