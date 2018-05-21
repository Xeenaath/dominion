package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Remodel() {
        super("Remodel", 4);
    }

    @Override
    public void play(Player p) {
        String chosenCardToTrash = p.chooseCard("choisissez la carte à écarter :", p.cardsInHand(), false);
        int remodelValue = p.cardsInHand().getCard(chosenCardToTrash).getCost()+2;
        p.trashFromHand(chosenCardToTrash);
        boolean done = false;
        while (!done){
            String chosenCardToBuy = p.chooseCard("choississez une carte à acheter :", p.getGame().availableSupplyCards(),false);
            if (remodelValue >= p.getGame().availableSupplyCards().getCard(chosenCardToBuy).getCost()){
                p.gain(chosenCardToBuy);
                done = true;
            }
        }
    }
}