package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Militia() {
        super("Militia", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.incrementMoney(2);
        for (int i = 0; i < p.getGame().numberOfPlayers(); i++) {
            Player player = p.getGame().getPlayer(i);
            if (player != p && !this.protectedPlayers.contains(player)) {
                while (player.cardsInHand().size() > 3) {
                    String answer = player.chooseCard("choississez une carte à défausser :", player.cardsInHand(), false);
                    player.discardFromHand(answer);
                }
            }
        }
    }
}