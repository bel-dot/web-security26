package chnu.edu.websecurity26.services;

import chnu.edu.websecurity26.models.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    // Initialize list with 3 starter entries
    private final List<Game> games = new ArrayList<>(List.of(
            new Game("1", "Sonic the Hedgehog (1991)", "Classic 16-bit high-speed side-scroller featuring Sonic.", "Platformer"),
            new Game("2", "Deltarune", "Episodic RPG following Kris and Susie through Dark Worlds.", "RPG"),
            new Game("3", "Celeste", "Narrative-driven precision platformer about conquering Mount Celeste.", "Platformer")
    ));

    // READ ALL
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    // READ ONE
    public Game getGameById(String id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst().orElse(null);
    }

    // CREATE
    public Game createGame(Game game) {
        if (game.getId() == null || game.getId().isBlank()) {
            game.setId(UUID.randomUUID().toString());
        }
        games.add(game);
        return game;
    }

    // UPDATE
    public Game updateGame(Game updatedGame) {
        Game oldGame = getGameById(updatedGame.getId());
        if(oldGame != null) {
            games.remove(oldGame);
            games.add(updatedGame);
            return updatedGame;
        }
        else return null;
    }

    // DELETE
    public void deleteGame(String id) {
        Game foundYou = getGameById(id);
        if(foundYou != null)
            games.remove(foundYou);
    }
}