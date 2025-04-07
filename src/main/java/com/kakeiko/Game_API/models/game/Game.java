package com.kakeiko.Game_API.models.game;
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
import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.publisher.Publisher;

@Table(name = "game")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game{

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private Integer year_release;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    private String description;

    private String platform;
}