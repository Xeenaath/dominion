package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Feast() {
        super("Feast", 4);
    }

    @Override
    public void play(Player p) {
        p.trashFromHand("Feast");
        boolean done = false;
        while (!done){
            String chosenCard = p.chooseCard("Choississez une carte qui vaut moins de 6 pièces :", p.getGame().availableSupplyCards(),false);
            if (p.getGame().getFromSupply(chosenCard).getCost() <=5) {
                p.gain(chosenCard);
                done = true;
            }
        }

    }
}