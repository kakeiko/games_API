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

import com.kakeiko.Game_API.models.franchise.Franchise;
import com.kakeiko.Game_API.models.franchise.FranchiseDeleteDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseEditDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseRequestDTO;
import com.kakeiko.Game_API.models.franchise.FranchiseResponseDTO;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.services.FranchiseService;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {
    
    @Autowired
    private FranchiseService franchiseService;

    @PostMapping
    public ResponseEntity<Franchise> create(@RequestBody FranchiseRequestDTO body){
        Franchise newFranchise = this.franchiseService.createFranchise(body);
        return ResponseEntity.ok(newFranchise);

    }

    @GetMapping
    public ResponseEntity<List<FranchiseResponseDTO>> getFranchise(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        
        List<FranchiseResponseDTO> allFranchises = this.franchiseService.getFranchise(page,size);
        return ResponseEntity.ok(allFranchises);

    }

    @GetMapping("/games/{id}")
    public ResponseEntity<List<GameRespondeDTO>> getGameByFranchise(@PathVariable UUID id){
        Franchise franchise = this.franchiseService.getFranchiseById(id);
        List<GameRespondeDTO> allGameByFranchise = this.franchiseService.getGameByFranchise(franchise);

        return ResponseEntity.ok(allGameByFranchise);
    }

    @PutMapping
    public ResponseEntity<Franchise> editFranchise(@RequestBody FranchiseEditDTO body){
        Franchise rewFranchise = this.franchiseService.editFranchise(body);
        return ResponseEntity.ok(rewFranchise);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFranchise(@RequestBody FranchiseDeleteDTO id){
        this.franchiseService.deleteFranchise(id);
        return ResponseEntity.ok("Franquia deleteda.");
    }
}