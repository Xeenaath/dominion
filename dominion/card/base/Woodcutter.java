package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bûcheron (Woodcutter)
 * 
 * +1 Achat.
 * +2 Pièces.
 */
public class Woodcutter extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Woodcutter(String name, int cost) {
        super("Bûcheron", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementBuys(1);
        p.incrementMoney(2);
    }
}