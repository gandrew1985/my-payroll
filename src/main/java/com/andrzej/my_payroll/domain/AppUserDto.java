package com.andrzej.my_payroll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class AppUserDto {

    private Long id;
    private String username;
    private String password;
    private String role;


}
