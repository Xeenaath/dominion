package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * <p>
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public ThroneRoom() {
        super("Throne Room", 4);
    }

    @Override
    public void play(Player p) {
        boolean done = false;
        String chosenCardActionName = p.chooseCard("Quelle carte Action voulez-vous jouer 2 fois ? :", p.getActionCards(), false);
        if (!chosenCardActionName.equals("")) {
            Card card = p.getActionCards().getCard(chosenCardActionName);
            card.play(p);
            p.playCard(card);
        }
    }
}