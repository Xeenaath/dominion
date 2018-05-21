package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * <p>
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Mine() {
        super("Mine", 5);
    }

    @Override
    public void play(Player p) {
        boolean done = false;
        while (!done) {
            String treasureCardName = p.chooseCard("choississez une carte Trésor à écarter :", p.getTreasureCards(), false);
            if (!treasureCardName.equals("")) {
                boolean done2 = false;
                Card treasureCardToTrash = p.cardsInHand().getCard(treasureCardName);
                while (!done2) {
                    String chosenCard = p.chooseCard("Choisissez une carte Trésor à recevoir :", p.getGame().availableTreasureSupplyCards(), false);
                    if (!chosenCard.equals("")) {
                        Card card = p.getGame().getFromSupply(chosenCard);
                        if (card.getCost() <= treasureCardToTrash.getCost() + 3) {
                            p.moveCardFromSupplyToHand(chosenCard);
                            p.trashFromHand(treasureCardName);
                            done = true;
                            done2 = true;
                        } else {
                            System.out.println("Action invalide");
                        }
                    } else {
                        System.out.println("carte non disponible dans la réserve");
                        done = true;
                        done2 = true;
                    }

                }

            } else {
                System.out.println("aucun effet");
                done = true;

            }
        }

    }
}