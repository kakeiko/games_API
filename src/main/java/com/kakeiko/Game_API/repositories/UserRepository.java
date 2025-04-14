package com.kakeiko.Game_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.kakeiko.Game_API.models.users.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByName(String name);
}
