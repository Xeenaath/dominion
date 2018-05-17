package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public ActionCard(String name, int cost) {
        super(name, cost);
    }
}