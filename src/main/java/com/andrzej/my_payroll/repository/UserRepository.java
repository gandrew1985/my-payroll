package com.andrzej.my_payroll.repository;

import com.andrzej.my_payroll.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Override
    List<AppUser> findAll();

    @Override
    Optional<AppUser> findById(Long id);

    @Override
    AppUser save(AppUser appUser);

    @Override
    void deleteById(Long id);

    AppUser findByUsername(String username);
}
