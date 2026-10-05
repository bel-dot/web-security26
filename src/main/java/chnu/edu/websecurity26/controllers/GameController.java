package chnu.edu.websecurity26.controllers;

import chnu.edu.websecurity26.models.Game;
import chnu.edu.websecurity26.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService = new GameService();

    @GetMapping
    public List<Game> getAll() { return gameService.getAllGames(); }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable String id) {
        return gameService.getGameById(id);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping
    public Game updateGame(@RequestBody Game game) {
        return gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
    }
}
