package com.andrzej.my_payroll.service;

import com.andrzej.my_payroll.domain.AppUser;
import com.andrzej.my_payroll.exception.UserNotFoundException;
import com.andrzej.my_payroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    private UserRepository repository;

    @Autowired
    public DbService(UserRepository repository) {
        this.repository = repository;
    }

    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    public AppUser getUser(final Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public AppUser saveUser(final AppUser appUser) {
        return repository.save(appUser);
    }

    public void deleteUser(final Long id) {
        repository.deleteById(id);
    }
}
