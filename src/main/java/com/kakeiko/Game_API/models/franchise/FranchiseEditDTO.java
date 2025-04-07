package com.kakeiko.Game_API.models.franchise;

import java.util.UUID;

public record FranchiseEditDTO(UUID id, String title, String developer, String description) {

}
