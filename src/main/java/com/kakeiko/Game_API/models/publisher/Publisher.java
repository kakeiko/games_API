package com.kakeiko.Game_API.models.publisher;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.UUID;

@Table(name = "publisher")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher{
    
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Integer year_foundation;

    private String size;

    private String description;

}