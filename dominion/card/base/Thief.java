package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Thief() {
        super("Thief", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        int numberOfTrashedCards = 0;
        for (int i = 0; i < p.getGame().numberOfPlayers(); i++) {
            Player player = p.getGame().getPlayer(i);
            if (player != p && !this.protectedPlayers.contains(player)) {
                Card card1 = player.drawCard();
                Card card2 = player.drawCard();
                CardList treasureCards = new CardList();
                if (card1 instanceof TreasureCard ){
                    treasureCards.add(card1);
                }
                if (card2 instanceof TreasureCard ){
                    treasureCards.add(card2);
                }
                String answer = p.chooseCard("Quelle carte souhaitez-vous voir écartée ?", treasureCards,false );
                if (answer.equals("")){
                    player.placeCardInDiscard(card1);
                    player.placeCardInDiscard(card2);
                } else {
                    numberOfTrashedCards++;
                    if (card1.getName().equals(answer)){
                        player.placeCardInDiscard(card2);
                        player.getGame().trashCard(card1);
                    } else {
                        player.placeCardInDiscard(card1);
                        player.getGame().trashCard(card2);
                    }
                }
            }
        }
        CardList choices = p.getGame().getLastXCardsFromTrash(numberOfTrashedCards);
        boolean done = false;
        while (!done) {
            String chosenCard = p.chooseCard("Voulez-vous voler une de ces cartes ? (vide pour passer) :", choices,true);
            if (!chosenCard.equals("")){
                Card gainedCard = p.gainCardFromTrash(chosenCard);
                choices.remove(gainedCard);
            } else {
                done = true;
            }
        }
    }
}