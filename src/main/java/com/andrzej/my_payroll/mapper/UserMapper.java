package com.andrzej.my_payroll.mapper;

import com.andrzej.my_payroll.domain.AppUser;
import com.andrzej.my_payroll.domain.AppUserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public AppUserDto mapToUserDto(AppUser appUser) {
        return new AppUserDto(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getRole());
    }

    public AppUser mapToUser(AppUserDto appUserDto) {
        return new AppUser(
                appUserDto.getUsername(),
                appUserDto.getPassword(),
                appUserDto.getRole());
    }

    public List<AppUserDto> mapToListUserDto(List<AppUser> appUserList) {
        return appUserList.stream()
                .map(user -> new AppUserDto(user.getId(), user.getUsername(),
                        user.getPassword(), user.getRole()))
                .collect(Collectors.toList());
    }
}
