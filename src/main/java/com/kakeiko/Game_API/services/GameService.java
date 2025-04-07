package com.kakeiko.Game_API.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kakeiko.Game_API.models.developer.Developer;
import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.game.GameDeleteDTO;
import com.kakeiko.Game_API.models.game.GameEditDTO;
import com.kakeiko.Game_API.models.game.GameRequestDTO;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.models.publisher.Publisher;
import com.kakeiko.Game_API.repositories.DeveloperRepository;
import com.kakeiko.Game_API.repositories.FranchiseRepository;
import com.kakeiko.Game_API.repositories.GameRepository;
import com.kakeiko.Game_API.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(GameRequestDTO data){
        Game newGame = new Game();
        
        Developer developer = developerRepository.findByName(data.developer()).orElseThrow(() -> new RuntimeException("Developer não encontrado!"));

        Franchise franchise = franchiseRepository.findByTitle(data.franchise()).orElseThrow(() -> new RuntimeException("Franchise não encontrado!"));

        Publisher publisher = publisherRepository.findByName(data.publisher()).orElseThrow(() -> new RuntimeException("Publisher não encontrado!"));

        newGame.setTitle(data.title());
        newGame.setYear_release(data.year_release());
        newGame.setGender(data.gender());
        newGame.setDeveloper(developer);
        newGame.setPublisher(publisher);
        newGame.setFranchise(franchise);
        newGame.setDescription(data.description());
        newGame.setPlatform(data.platform());

        return gameRepository.save(newGame);
    }
    
    public List<GameRespondeDTO> getGame(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Game> gamePage = this.gameRepository.findAll(pageable);

        return gamePage.map(game -> new GameRespondeDTO(game.getId(), game.getTitle(), game.getYear_release(), game.getGender(), game.getDeveloper(), game.getPublisher(), game.getFranchise(), game.getDescription(), game.getPlatform())).stream().toList();
    }
    
    public Game editGame(GameEditDTO data) {
        Game editGame = getGameByid(data.id());

        Developer developer = developerRepository.findByName(data.developer()).orElseThrow(() -> new RuntimeException("Developer não encontrado!"));

        Franchise franchise = franchiseRepository.findByTitle(data.franchise()).orElseThrow(() -> new RuntimeException("Franchise não encontrado!"));

        Publisher publisher = publisherRepository.findByName(data.publisher()).orElseThrow(() -> new RuntimeException("Publisher não encontrado!"));

        editGame.setTitle(data.title());
        editGame.setYear_release(data.year_release());
        editGame.setGender(data.gender());
        editGame.setDeveloper(developer);
        editGame.setPublisher(publisher);
        editGame.setFranchise(franchise);
        editGame.setDescription(data.description());
        editGame.setPlatform(data.platform());

        return gameRepository.save(editGame);
    }

    public Game getGameByid(UUID id) {
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game não encontrado!"));

        return game;
    }

    public void deleteGame(GameDeleteDTO id) {
        Game gameDelete = getGameByid(id.id());
        this.gameRepository.delete(gameDelete);
    }
}
