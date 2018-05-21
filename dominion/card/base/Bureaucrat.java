package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Bureaucrat() {
        super("Bureaucrat", 4);
    }

    @Override
    public void play(Player p) {
        p.placeCardOnDraw("Silver");
        for (int i = 0; i < p.getGame().numberOfPlayers(); i++){
            Player player = p.getGame().getPlayer(i);
            if (player != p){
                String answer = player.chooseCard("choississez une carte victoire à dévoiler :", player.getVictoryCards(), false);
                if (!answer.equals("")) {
                    player.moveCardFromHandToDraw(player.cardsInHand().getCard(answer));
                }
            }
        }
    }
}