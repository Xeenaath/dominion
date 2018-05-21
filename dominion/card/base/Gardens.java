package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends VictoryCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Gardens() {
        super("Jardin", 4);
    }

    @Override
    public void play(Player p) {
        //Do nothing
    }

    @Override
    public int victoryValue(Player p) {
        int points = p.totalCards().size() / 10;
        return points;
    }
}