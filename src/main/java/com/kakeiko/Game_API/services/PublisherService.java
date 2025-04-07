package com.kakeiko.Game_API.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.models.publisher.Publisher;
import com.kakeiko.Game_API.models.publisher.PublisherDeleteDTO;
import com.kakeiko.Game_API.models.publisher.PublisherRequestDTO;
import com.kakeiko.Game_API.models.publisher.PublisherResponseDTO;
import com.kakeiko.Game_API.repositories.GameRepository;
import com.kakeiko.Game_API.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private GameRepository gameRepository;

    public Publisher createPublisher(PublisherRequestDTO data){
        
        Publisher newPublisher = new Publisher();

        newPublisher.setName(data.name());
        newPublisher.setYear_foundation(data.year_foundation());
        newPublisher.setSize(data.size());
        newPublisher.setDescription(data.description());

        return publisherRepository.save(newPublisher);
    }
    
    public List<PublisherResponseDTO> getPublisher(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Publisher> publisherPage = this.publisherRepository.findAll(pageable);

        return publisherPage.map(publisher -> new PublisherResponseDTO(publisher.getId(), publisher.getName(), publisher.getYear_foundation(), publisher.getSize(), publisher.getDescription())).stream().toList();
    }
   
    public Publisher getPublisherById(UUID id) {
        Publisher publisher = this.publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher não encontrado!"));

        return publisher;
    }
    
    public List<GameRespondeDTO> getGameByPublisher(Publisher publisher) {
        List<Game> gamesByPublisher = this.gameRepository.findByPublisher(publisher).orElseThrow(() -> new RuntimeException("Game não encontrado!"));

        return gamesByPublisher.stream().map(game -> new GameRespondeDTO(game.getId(), game.getTitle(), game.getYear_release(), game.getGender(), game.getDeveloper(), game. getPublisher(), game.getFranchise(), game.getDescription(), game.getPlatform())).collect(Collectors.toList());
    }

    public Publisher editPublisher(PublisherResponseDTO data) {
        Publisher editPublisher = getPublisherById(data.id());

        editPublisher.setName(data.name());
        editPublisher.setYear_foundation(data.year_foundation());
        editPublisher.setSize(data.size());
        editPublisher.setDescription(data.description());

        return publisherRepository.save(editPublisher);
    }

    public void deletePublisher(PublisherDeleteDTO id) {
        Publisher publisherDelete = this.getPublisherById(id.id());
        this.publisherRepository.delete(publisherDelete);
    }
}
