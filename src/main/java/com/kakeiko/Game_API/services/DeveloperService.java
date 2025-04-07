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
import com.kakeiko.Game_API.models.developer.DeveloperDeleteDTO;
import com.kakeiko.Game_API.models.developer.DeveloperRequestDTO;
import com.kakeiko.Game_API.models.developer.DeveloperResponseDTO;
import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.repositories.DeveloperRepository;
import com.kakeiko.Game_API.repositories.GameRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeveloperService{

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private GameRepository gameRepository;

    public Developer createDeveloper(DeveloperRequestDTO data){
        Developer newDeveloper = new Developer();

        newDeveloper.setName(data.name());
        newDeveloper.setYear_foundation(data.year_foundation());
        newDeveloper.setSize(data.size());
        newDeveloper.setOwner(data.owner());
        newDeveloper.setDescription(data.description());

        return developerRepository.save(newDeveloper);
    }

    public List<DeveloperResponseDTO> getDeveloper(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Developer> developerPage = this.developerRepository.findAll(pageable);
        return developerPage.map(developer ->new DeveloperResponseDTO(developer.getId(), developer.getName(), developer.getYear_foundation(), developer.getSize(), developer.getOwner(), developer.getDescription())).stream().toList();
    }

    public Developer getDeveloperById(UUID id){
        Developer developer = this.developerRepository.findById(id).orElseThrow(() -> new RuntimeException("Developer não encontrado!"));
        
        return developer;
    }

    public List<GameRespondeDTO> getGameByDeveloper(Developer developer){
        List<Game> gamesByDeveloper = this.gameRepository.findByDeveloper(developer).orElseThrow(() -> new RuntimeException("Game não encontrado!"));

        return gamesByDeveloper.stream().map(game -> new GameRespondeDTO(game.getId(), game.getTitle(), game.getYear_release(), game.getGender(), game.getDeveloper(), game. getPublisher(), game.getFranchise(), game.getDescription(), game.getPlatform())).collect(Collectors.toList());
    }

    public Developer editDeveloper(DeveloperResponseDTO data) {
        Developer editDeveloper = getDeveloperById(data.id());
        
        editDeveloper.setName(data.name());
        editDeveloper.setYear_foundation(data.year_foundation());
        editDeveloper.setSize(data.size());
        editDeveloper.setOwner(data.owner());
        editDeveloper.setDescription(data.description());

        return developerRepository.save(editDeveloper);
    }

    public void deleteDeveloper(DeveloperDeleteDTO id) {
        Developer developerDelete = getDeveloperById(id.id());
        this.developerRepository.delete(developerDelete);
    }
}