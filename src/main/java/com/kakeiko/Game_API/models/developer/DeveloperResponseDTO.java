package com.kakeiko.Game_API.models.developer;

import java.util.UUID;

public record DeveloperResponseDTO(UUID id,String name, Integer year_foundation, String size, String owner, String description) {
    
}
