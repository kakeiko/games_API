package com.kakeiko.Game_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakeiko.Game_API.models.developer.Developer;
import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.publisher.Publisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID>{

    public Optional<List<Game>> findByDeveloper(Developer developer);
    public Optional<List<Game>> findByFranchise(Franchise franchise);
    public Optional<List<Game>> findByPublisher(Publisher publisher);
}