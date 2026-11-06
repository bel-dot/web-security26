package chnu.edu.websecurity26.services;

import chnu.edu.websecurity26.models.Game;
import chnu.edu.websecurity26.repositories.GameRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    // Initialize list with 3 starter entries
    private final List<Game> games = new ArrayList<>(List.of(
            new Game("1", "Sonic the Hedgehog (1991)", "Classic 16-bit high-speed side-scroller featuring Sonic.", "Platformer"),
            new Game("2", "Deltarune", "Episodic RPG following Kris and Susie through Dark Worlds.", "RPG"),
            new Game("3", "Celeste", "Narrative-driven precision platformer about conquering Mount Celeste.", "Platformer")
    ));

    private final GameRepository gameRepository;

    @PostConstruct
    void init() {
        gameRepository.deleteAll();
        games.forEach(this::createGame);
    }

    // READ ALL
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // READ ONE
    public Game getGameById(String id) {
        return gameRepository.findById(id).orElse(null);
    }

    // CREATE
    public Game createGame(Game game) {
        if (game.getId() == null || game.getId().isBlank()) {
            game.setId(UUID.randomUUID().toString());
        }
        return gameRepository.save(game);
    }

    // UPDATE
    public Game updateGame(Game updatedGame) {
        Game oldGame = getGameById(updatedGame.getId());
        if(oldGame != null) {
            oldGame.setName(updatedGame.getName());
            oldGame.setDescription(updatedGame.getDescription());
            oldGame.setGenre(updatedGame.getGenre());
            return gameRepository.save(oldGame);
        }
        else return null;
    }

    // DELETE
    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }
}