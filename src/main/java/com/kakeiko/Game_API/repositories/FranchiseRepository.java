package com.kakeiko.Game_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakeiko.Game_API.models.franchise.Franchise;

import java.util.Optional;
import java.util.UUID;

public interface FranchiseRepository extends JpaRepository<Franchise, UUID>{
    Optional<Franchise> findByTitle(String title);
}