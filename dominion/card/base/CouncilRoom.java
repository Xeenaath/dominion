package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public CouncilRoom() {
        super("Council Room", 5);
    }

    @Override
    public void play(Player p) {
        p.draw(4);
        p.incrementBuys(1);
        for (int i = 0; i < p.getGame().numberOfPlayers(); i++) {
            Player player = p.getGame().getPlayer(i);
            if (player != p) {
                player.draw(1);
            }
        }
    }
}