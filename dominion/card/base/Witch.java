package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {
    /**
     * Constructeur simple
     */
    public Witch() {
        super("Witch", 5);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.draw(2);
        for (Player otherplayer : p.getGame().otherPlayers(p)){
            if (!this.protectedPlayers.contains(otherplayer)){
                otherplayer.gain("Curse");
            }
        }
    }
}