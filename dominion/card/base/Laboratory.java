package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Laboratory(String name, int cost) {
        super("Laboratoire", 5);
    }

    @Override
    public void play(Player p) {

    }
}