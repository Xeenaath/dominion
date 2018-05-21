package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main.
 * Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Moneylender() {
        super("Moneylender", 4);
    }

    @Override
    public void play(Player p) {
        for (Card card : p.cardsInHand()){
            if (card.getName().equals("Copper")){
                p.trashFromHand(card.getName());
                p.incrementMoney(3);
                break;
            }
        }
    }
}