package com.andrzej.my_payroll.service;

import com.andrzej.my_payroll.domain.AppUser;
import com.andrzej.my_payroll.domain.AppUserDto;
import com.andrzej.my_payroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public AppUser encryptPassword(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();

        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserDto.getPassword()));
        appUser.setRole(appUserDto.getRole());

        return appUser;
    }
}
