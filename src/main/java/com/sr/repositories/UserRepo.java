package com.sr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sr.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
