package com.kakeiko.Game_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kakeiko.Game_API.models.developer.Developer;

import java.util.Optional;
import java.util.UUID;

public interface DeveloperRepository extends JpaRepository<Developer, UUID>{
    Optional<Developer> findByName(String name);
}