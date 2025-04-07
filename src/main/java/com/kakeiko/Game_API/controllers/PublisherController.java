package com.kakeiko.Game_API.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.models.publisher.Publisher;
import com.kakeiko.Game_API.models.publisher.PublisherDeleteDTO;
import com.kakeiko.Game_API.models.publisher.PublisherRequestDTO;
import com.kakeiko.Game_API.models.publisher.PublisherResponseDTO;
import com.kakeiko.Game_API.services.PublisherService;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody PublisherRequestDTO body){
        Publisher newPublisher = this.publisherService.createPublisher(body);
        return ResponseEntity.ok(newPublisher);
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getPublisher(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        
        List<PublisherResponseDTO> allPublishers = this.publisherService.getPublisher(page,size);
        return ResponseEntity.ok(allPublishers);

    }

    @GetMapping("/games/{id}")
    public ResponseEntity<List<GameRespondeDTO>> getGameByPublisher(@PathVariable UUID id){
        Publisher publisher = this.publisherService.getPublisherById(id);
        List<GameRespondeDTO> allGameByPublisher = this.publisherService.getGameByPublisher(publisher);

        return ResponseEntity.ok(allGameByPublisher);
    }

    @PutMapping
    public ResponseEntity<Publisher> editPublisher(@RequestBody PublisherResponseDTO body){
        Publisher rewPublisher = this.publisherService.editPublisher(body);
        return ResponseEntity.ok(rewPublisher);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePublisher(@RequestBody PublisherDeleteDTO id){
        this.publisherService.deletePublisher(id);
        return ResponseEntity.ok("Publisher deletada.");
    }
}
