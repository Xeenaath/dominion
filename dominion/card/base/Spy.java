package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * <p>
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {
    /**
     * Constructeur simple
     */

    public Spy() {
        super("Spy", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.draw(1);
        p.incrementActions(1);
        for (int i = 0; i < p.getGame().numberOfPlayers(); i++) {
            Player player = p.getGame().getPlayer(i);
            if (!this.protectedPlayers.contains(player)) {
                Card card = player.drawCard();
                System.out.println(card.getName());
                String choix = p.choose("voulez-vous mettre cette carte dans la défausse ? (y/n) : ", Arrays.asList("y", "n"), false);
                if (choix.equals("y")) {
                    player.placeCardInDiscard(card);
                } else {
                    player.placeCardOnTopOfDraw(card);
                }
            }
        }
    }
}