package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Réaction
 * Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public ReactionCard(String name, int cost) {
        super(name, cost);
    }
}