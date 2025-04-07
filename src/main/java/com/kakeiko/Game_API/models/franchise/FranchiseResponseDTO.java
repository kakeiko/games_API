package com.kakeiko.Game_API.models.franchise;

import java.util.UUID;

import com.kakeiko.Game_API.models.developer.Developer;

public record FranchiseResponseDTO(UUID id, String title, Developer developer, String description) {
    
}