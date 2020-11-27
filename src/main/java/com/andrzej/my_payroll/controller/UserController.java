package com.andrzej.my_payroll.controller;

import com.andrzej.my_payroll.domain.AppUserDto;
import com.andrzej.my_payroll.exception.UserNotFoundException;
import com.andrzej.my_payroll.mapper.UserMapper;
import com.andrzej.my_payroll.service.DbService;
import com.andrzej.my_payroll.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class UserController {

    private DbService service;
    private UserMapper mapper;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(DbService service, UserMapper mapper, UserDetailsServiceImpl userDetailsService) {
        this.service = service;
        this.mapper = mapper;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/getAllUsers")
    public List<AppUserDto> getAllUsers() {
        return mapper.mapToListUserDto(service.getAllUsers());
    }

    @GetMapping("/getUser")
    public AppUserDto getUser(@RequestParam Long id) throws UserNotFoundException {
        return mapper.mapToUserDto(service.getUser(id));
    }

    @PostMapping(value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody AppUserDto appUserDto) {
        service.saveUser(userDetailsService.encryptPassword(appUserDto));
    }

    @PutMapping(value = "/editUser", consumes = APPLICATION_JSON_VALUE)
    public AppUserDto editUser(@RequestBody AppUserDto appUserDto) {
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUser(appUserDto)));
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
    }


}
