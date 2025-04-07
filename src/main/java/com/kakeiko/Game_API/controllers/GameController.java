package com.kakeiko.Game_API.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakeiko.Game_API.models.game.Game;
import com.kakeiko.Game_API.models.game.GameDeleteDTO;
import com.kakeiko.Game_API.models.game.GameEditDTO;
import com.kakeiko.Game_API.models.game.GameRequestDTO;
import com.kakeiko.Game_API.models.game.GameRespondeDTO;
import com.kakeiko.Game_API.services.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody GameRequestDTO body){
        Game newGame = this.gameService.createGame(body);
        return ResponseEntity.ok(newGame);
    }

    @GetMapping
    public ResponseEntity<List<GameRespondeDTO>> getGame(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        
        List<GameRespondeDTO> allGames = this.gameService.getGame(page,size);
        return ResponseEntity.ok(allGames);

    }

    @PutMapping
    public ResponseEntity<Game> editGame(@RequestBody GameEditDTO body){
        Game rewGame = this.gameService.editGame(body);
        return ResponseEntity.ok(rewGame);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteGame(@RequestBody GameDeleteDTO id){
        this.gameService.deleteGame(id);
        return ResponseEntity.ok("deu certo");
    } 
}
