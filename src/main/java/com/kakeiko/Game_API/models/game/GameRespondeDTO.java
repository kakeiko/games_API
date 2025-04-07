package com.kakeiko.Game_API.models.game;

import java.util.UUID;

import com.kakeiko.Game_API.models.developer.Developer;
import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.publisher.Publisher;

public record GameRespondeDTO(UUID id, String title, Integer year_release, String gender, Developer developer, Publisher publisher, Franchise franchise, String description, String platform) {
    
}
