package com.pay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pay.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}