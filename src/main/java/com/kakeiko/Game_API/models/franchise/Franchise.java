package com.kakeiko.Game_API.models.franchise;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;

import java.util.UUID;

import com.kakeiko.Game_API.models.developer.Developer;

@Table(name = "franchise")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Franchise{

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;


    private String description;
}