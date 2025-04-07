package com.kakeiko.Game_API.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kakeiko.Game_API.models.developer.Developer;
import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.franchise.FranchiseDeleteDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseEditDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseRequestDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseResponseDTO;
import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.repositories.DeveloperRepository;
import com.kakeiko.Game_API.repositories.FranchiseRepository;
import com.kakeiko.Game_API.repositories.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FranchiseService {
    
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private GameRepository gameRepository;

    public Franchise createFranchise(FranchiseRequestDTO data){
        Franchise newFranchise = new Franchise();
        
        Developer developer = developerRepository.findByName(data.developer()).orElseThrow(() -> new RuntimeException("Developer não encontrado!"));

        newFranchise.setTitle(data.title());
        newFranchise.setDeveloper(developer);
        newFranchise.setDescription(data.description());

        return franchiseRepository.save(newFranchise);
    }

    public List<FranchiseResponseDTO> getFranchise(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Franchise> franchisePage = this.franchiseRepository.findAll(pageable);

        return franchisePage.map(franchise -> new FranchiseResponseDTO(franchise.getId(), franchise.getTitle(), franchise.getDeveloper(), franchise.getDescription())).stream().toList();
    }

    public Franchise getFranchiseById(UUID id) {
        Franchise franchise = this.franchiseRepository.findById(id).orElseThrow(() -> new RuntimeException("franchise não encontrado!"));

        return franchise;
    }

    public List<GameRespondeDTO> getGameByFranchise(Franchise franchise) {
        List<Game> gamesByFranchise = this.gameRepository.findByFranchise(franchise).orElseThrow(() -> new RuntimeException("Game não encontrado!"));

        return gamesByFranchise.stream().map(game -> new GameRespondeDTO(game.getId(), game.getTitle(), game.getYear_release(), game.getGender(), game.getDeveloper(), game. getPublisher(), game.getFranchise(), game.getDescription(), game.getPlatform())).collect(Collectors.toList());
    }

    public Franchise editFranchise(FranchiseEditDTO data) {
        Franchise editFranchise = getFranchiseById(data.id());

        Developer developer = developerRepository.findByName(data.developer()).orElseThrow(() -> new RuntimeException("franchise não encontrado!"));

        editFranchise.setTitle(data.title());
        editFranchise.setDeveloper(developer);
        editFranchise.setDescription(data.description());

        return franchiseRepository.save(editFranchise);
    }

    public void deleteFranchise(FranchiseDeleteDTO id) {
        Franchise franchiseDelete = getFranchiseById(id.id());
        this.franchiseRepository.delete(franchiseDelete);
    }
}
