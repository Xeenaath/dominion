package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {
    protected ArrayList<Player> protectedPlayers = new ArrayList<Player>();
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public AttackCard(String name, int cost) {
        super(name, cost);
    }

    @Override
    public void play(Player p) {
        for (Player player : p.getGame().otherPlayers(p)){
            Card card = player.cardsInHand().getCard("Moat");
            if (card != null){
                String answer = player.choose("voulez-vous bloquer l'attaque avec la carte Moat ? (y/n) :",   Arrays.asList("y", "n"), false);
                if (answer.equals("y")){
                    protectedPlayers.add(player);
                }
            }
        }
    }
}