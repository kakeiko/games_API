package com.kakeiko.Game_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakeiko.Game_API.models.publisher.Publisher;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID>{
    Optional<Publisher> findByName(String name);
}