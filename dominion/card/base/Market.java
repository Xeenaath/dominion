package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 * 
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Market() {
        super("Market", 5);
    }

    @Override
    public void play(Player p) {
        p.draw(1);
        p.incrementActions(1);
        p.incrementBuys(1);
        p.incrementMoney(1);
    }
}