package com.kakeiko.Game_API.models.game;

public record GameRequestDTO(String title, Integer year_release, String gender, String developer, String publisher, String franchise, String description, String platform ) {
    
}
