package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Cellar() {
        super("Cellar", 2 );
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        boolean done = false;
        while (!done) {
            String chosenCardName = p.chooseCard("Choississez une carte à défausser (vide pour passer) :", p.cardsInHand(), true);
            if (!chosenCardName.equals("")){
                p.discardFromHand(chosenCardName);
                p.draw(1);
            } else {
                done = true;
            }
        }
    }
}