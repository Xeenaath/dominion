package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Workshop() {
        super("Workshop", 3);
    }

    @Override
    public void play(Player p) {
        boolean done = false;
        while (!done){
            String chosenCard = p.chooseCard("Choississez une carte qui vaut moins de 5 pièces :", p.getGame().availableSupplyCards(),false);
            if (p.getGame().getFromSupply(chosenCard).getCost() <=4) {
                p.gain(chosenCard);
                done = true;
            }
        }
    }
}