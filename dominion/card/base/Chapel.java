package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Chapel() {
        super("Chapel", 2 );
    }

    @Override
    public void play(Player p) {
        boolean done = false;
        int numberOfTrashedCards = 0;
        while (!done){
            String chosenCardName = p.chooseCard("Choissisez une carte à écarter (vide pour passer) :", p.cardsInHand(),true);
            if (!chosenCardName.equals("")){
                p.trashFromHand(chosenCardName);
                numberOfTrashedCards++;
                if (numberOfTrashedCards >= 4){
                    done = true;
                }
            } else {
                done = true;
            }
        }

    }
}