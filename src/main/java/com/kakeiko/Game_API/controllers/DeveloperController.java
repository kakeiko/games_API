package com.kakeiko.Game_API.controllers;

import com.kakeiko.Game_API.models.developer.Developer;
import com.kakeiko.Game_API.models.developer.DeveloperDeleteDTO;
import com.kakeiko.Game_API.models.developer.DeveloperRequestDTO;
import com.kakeiko.Game_API.models.developer.DeveloperResponseDTO;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.services.DeveloperService;

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

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {
    
    @Autowired
    private DeveloperService developerService;

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody DeveloperRequestDTO body){
        Developer newDeveloper = this.developerService.createDeveloper(body);
        return ResponseEntity.ok(newDeveloper);
    }

    @GetMapping
    public ResponseEntity<List<DeveloperResponseDTO>> getDeveloper(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        
        List<DeveloperResponseDTO> allDevelopers = this.developerService.getDeveloper(page,size);
        return ResponseEntity.ok(allDevelopers);

    }

    @GetMapping("/games/{id}")
    public ResponseEntity<List<GameRespondeDTO>> getGameByDeveloper(@PathVariable UUID id){

        Developer developer = this.developerService.getDeveloperById(id);
        List<GameRespondeDTO> allGameByDeveloper = this.developerService.getGameByDeveloper(developer);
        return ResponseEntity.ok(allGameByDeveloper);
    }

    @PutMapping
    public ResponseEntity<Developer> editDeveloper(@RequestBody DeveloperResponseDTO body){
        Developer rewDeveloper = this.developerService.editDeveloper(body);
        return ResponseEntity.ok(rewDeveloper);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDeveloper(@RequestBody DeveloperDeleteDTO id){
        this.developerService.deleteDeveloper(id);
        return ResponseEntity.ok("Developer deletado.");
    } 
}
