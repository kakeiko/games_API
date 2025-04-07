package com.kakeiko.Game_API.models.game;

import java.util.UUID;

public record GameEditDTO(UUID id, String title, Integer year_release, String gender, String developer, String publisher, String franchise, String description, String platform)  {

}
