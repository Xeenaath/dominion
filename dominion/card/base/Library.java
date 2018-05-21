package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {
    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Library() {
        super("Library", 5);
    }

    @Override
    public void play(Player p) {
        while (p.cardsInHand().size() <= 7){
           CardList drawnCards = p.draw(1);
           Card card = drawnCards.get(0);
            if (card instanceof ActionCard) {
                String yesNo = p.choose("Mettre de côté ? (y/n) :",   Arrays.asList("y", "n"), false);
                if (yesNo.equals("y")){
                    p.discardFromHand(card.getName());
                }
            } else {

            }
        }
    }
}