package com.kakeiko.Game_API.models.publisher;

import java.util.UUID;

public record PublisherResponseDTO(UUID id, String name, Integer year_foundation, String size, String description) {
    
}
