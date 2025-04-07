package com.kakeiko.Game_API.models.developer;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.UUID;


@Table(name = "developer")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Developer{

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Integer year_foundation;

    private String size;

    private String owner;

    private String description;

}