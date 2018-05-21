package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Chancellor() {
        super("Chancellor", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(2);
        String answer = p.choose("Défausser votre deck ? (y/n) : ",  Arrays.asList("y", "n"), false);
        if (answer.equals("y")){
            p.discardDraw();
        }
    }
}