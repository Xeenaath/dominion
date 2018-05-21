package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.
 */
public class Adventurer extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Adventurer() {
        super("Adventurer", 6);
    }

    @Override
    public void play(Player p) {
        CardList treasureCards = new CardList();
        CardList notTreasureCards = new CardList();
        while (treasureCards.size() < 2) {
            Card drawnCard = p.drawCard();
            if (drawnCard instanceof TreasureCard){
                treasureCards.add(drawnCard);
            } else {
                notTreasureCards.add(drawnCard);
            }
        }
        for (Card card : treasureCards){
            p.placeCardOnHand(card);
        }
        for (Card card : notTreasureCards){
            p.placeCardInDiscard(card);
        }
    }
}