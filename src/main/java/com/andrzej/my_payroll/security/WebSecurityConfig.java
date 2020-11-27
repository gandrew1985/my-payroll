package com.andrzej.my_payroll.security;

import com.andrzej.my_payroll.domain.AppUser;
import com.andrzej.my_payroll.repository.UserRepository;
import com.andrzej.my_payroll.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/getAllUsers").hasRole("ADMIN")
                .antMatchers("/api/getUser").hasRole("ADMIN")
                .antMatchers("/api/createUser").hasRole("ADMIN")
                .antMatchers("/api/editUser").hasRole("ADMIN")
                .antMatchers("/api/deleteUser").hasRole("ADMIN")
                .antMatchers("/api/testApi").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdmin() {
        AppUser admin = new AppUser("Andrzej",
                bCryptPasswordEncoder.encode("Golab123"), "ROLE_ADMIN");
        userRepository.save(admin);
    }
}
