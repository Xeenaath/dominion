package dominion;

import java.util.*;

import dominion.card.*;
import dominion.card.common.*;

/**
 * Class représentant une partie de Dominion
 */
public class Game {
    /**
     * Tableau contenant les joueurs de la partie
     */
    private Player[] players;

    /**
     * Index du joueur dont c'est actuellement le tour
     */
    private int currentPlayerIndex;

    /**
     * Liste des piles dans la réserve du jeu.
     * <p>
     * On suppose ici que toutes les listes contiennent des copies de la même
     * carte.
     * Ces piles peuvent être vides en cours de partie si toutes les cartes de
     * la pile ont été achetées ou gagnées par les joueurs.
     */
    private List<CardList> supplyStacks;

    /**
     * Liste des cartes qui ont été écartées (trash)
     */
    private CardList trashedCards;

    private Scanner scanner;

    /**
     *
     */

    /**
     * Constructeur
     *
     * @param playerNames   liste des noms des joueurs qui participent à la
     *                      partie. Le constructeur doit créer les objets correspondant aux joueurs
     * @param kingdomStacks liste de piles de réserve à utiliser correspondant
     *                      aux cartes "royaume" à utiliser dans la partie, auxquelles le
     *                      constructeur doit ajouter les piles "communes":
     *                      - 60 Copper
     *                      - 40 Silver
     *                      - 30 Gold
     *                      - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
     */
    public Game(String[] playerNames, List<CardList> kingdomStacks) {
        this.scanner = new Scanner(System.in);

        instanciateSupplyStack(kingdomStacks, playerNames);

        instanciatePlayers(playerNames);

        this.currentPlayerIndex = 0;
    }

    private void instanciatePlayers(String[] playerNames) {
        this.players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            Player a = new Player(playerNames[i], this);
            this.players[i] = a;
        }
    }

    private void instanciateSupplyStack(List<CardList> kingdomStacks, String[] playerNames) {
        this.supplyStacks = new ArrayList<CardList>();// à faire avec actions + cartes à gauche


        CardList gold = new CardList();
        CardList silver = new CardList();
        CardList copper = new CardList();
        CardList province = new CardList(); // 8 Pts Victoire
        CardList duchy = new CardList(); // 5 pts Victoire
        CardList estate = new CardList(); // 3 pts Victoire
        CardList curse = new CardList();
        for (int i = 0; i < 60; i++) {
            copper.add(new Copper());
            if (i < 40) {
                silver.add(new Silver());
            }
            if (i < 30) {
                gold.add(new Gold());
            }
        }


        int nbPlayers = playerNames.length;

        if (nbPlayers == 2) {
            for (int i = 0; i < 8; i++) {
                province.add(new Province());
                estate.add(new Estate());
                duchy.add(new Duchy());
            }
            for (int i = 0; i < 10; i++) {
                curse.add(new Curse());

            }
        }

        if (nbPlayers == 3) {
            for (int i = 0; i < 12; i++) {
                province.add(new Province());
                estate.add(new Estate());
                duchy.add(new Duchy());
            }
            for (int i = 0; i < 20; i++) {
                curse.add(new Curse());

            }
        }

        if (nbPlayers == 4) {
            for (int i = 0; i < 12; i++) {
                province.add(new Province());
                estate.add(new Estate());
                duchy.add(new Duchy());
            }
            for (int i = 0; i < 30; i++) {
                curse.add(new Curse());

            }


        }

        this.supplyStacks.addAll(kingdomStacks);
        this.supplyStacks.add(copper);
        this.supplyStacks.add(silver);
        this.supplyStacks.add(gold);
        this.supplyStacks.add(province);
        this.supplyStacks.add(estate);
        this.supplyStacks.add(duchy);
        this.supplyStacks.add(curse);
        this.trashedCards = new CardList();
    }

    /**
     * Renvoie le joueur correspondant à l'indice passé en argument
     * On suppose {@code index} est un indice valide du tableau
     * {@code this.players}
     *
     * @param index indice dans le tableau des joueurs du joueur à renvoyer
     */
    public Player getPlayer(int index) {

        return this.players[index];
    }

    /**
     * Renvoie le nombre de joueurs participant à la partie
     */
    public int numberOfPlayers() {
        /**int i = 0;
         while (players[i] != null){
         i++;
         }
         return i;**/

        return players.length;
    }

    /**
     * Renvoie l'indice du joueur passé en argument dans le tableau des
     * joueurs, ou -1 si le joueur n'est pas dans le tableau.
     */
    private int indexOfPlayer(Player p) {
        return Arrays.asList(players).indexOf(p);
    }

    /**
     * Renvoie la liste des adversaires du joueur passé en argument, dans
     * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
     *
     * @param p joueur dont on veut renvoyer la liste des adversaires. On
     *          suppose que {@code p} est bien dans le tableau des joueurs.
     * @return un {@code ArrayList} contenant les autres joueurs de la partie
     * en commençant par celui qui se trouve juste après {@code p} et en
     * terminant par celui qui se trouve juste avant (le tableau est considéré
     * comme cyclique c'est-à-dire qu'après le premier élément on revient au
     * premier).
     */
    public List<Player> otherPlayers(Player p) {
		/*List<Player> otherPlayers = new ArrayList<Player>();
		int i = indexOfPlayer(p)+1;
		while (this.players[i] != p) {
			System.out.println("OKAY 1");
			if (this.players[i] == null) {
				System.out.println("OKAY 2");
				i = 0;
			}
			otherPlayers.add(this.players[i]);
			i++;
		}
		return otherPlayers;*/
        List<Player> otherPlayers = new ArrayList<Player>();
        int indicePlayer = indexOfPlayer(p);
        for (int i = indicePlayer + 1; i < this.players.length; i++) {
            otherPlayers.add(this.players[i]);

        }
        for (int i = 0; i < indicePlayer; i++) {
            otherPlayers.add(this.players[i]);
        }
        return otherPlayers;
    }

    /**
     * Renvoie la liste des cartes qui sont disponibles à l'achat dans la
     * réserve.
     *
     * @return une liste de cartes contenant la première carte de chaque pile
     * non-vide de la réserve (cartes royaume et cartes communes)
     */
    public CardList availableSupplyCards() {
        CardList cartesDispo = new CardList();
        for (CardList cardList : this.supplyStacks) {
            if (cardList.size() > 0) {
                cartesDispo.add(cardList.get(0));
            }
        }
        return cartesDispo;
    }

    public CardList availableTreasureSupplyCards() {
        CardList cartesDispo = new CardList();
        for (CardList cardList : this.supplyStacks) {
            if (cardList.size() > 0) {
                Card card = cardList.get(0);
                if (card instanceof TreasureCard) {
                    cartesDispo.add(cardList.get(0));
                }
            }
        }
        return cartesDispo;

    }

    /**
     * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
     * de caractères.
     * <p>
     * Cette représentation comporte
     * - le nom du joueur dont c'est le tour
     * - la liste des piles de la réserve en indiquant pour chacune :
     * - le nom de la carte
     * - le nombre de copies disponibles
     * - le prix de la carte
     * si la pile n'est pas vide, ou "Empty stack" si la pile est vide
     */
    public String toString() {
        Player currentPlayer = this.players[this.currentPlayerIndex];
        String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
        for (List<Card> stack : this.supplyStacks) {
            if (stack.isEmpty()) {
                r += "[Empty stack]   ";
            } else {
                Card c = stack.get(0);
                r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
            }
        }
        r += "\n";
        return r;
    }

    /**
     * Renvoie une carte de la réserve dont le nom est passé en argument.
     *
     * @param cardName nom de la carte à trouver dans la réserve
     * @return la carte trouvée dans la réserve ou {@code null} si aucune carte
     * ne correspond
     */
    public Card getFromSupply(String cardName) {
        //System.out.println(this.supplyStacks);
        for (CardList cardList : this.supplyStacks) {
            if (cardList.getCard(cardName) != null) {
                return cardList.getCard(cardName);
            }
        }
        return null;
    }

    /**
     * Retire et renvoie une carte de la réserve
     *
     * @param cardName nom de la carte à retirer de la réserve
     * @return la carte retirée de la réserve ou {@code null} si aucune carte
     * ne correspond au nom passé en argument
     */

    public Card removeFromSupply(String cardName) {
        Card card = getFromSupply(cardName);
        if (card != null) {
            for (CardList cardlist : this.supplyStacks) {
                if (cardlist.remove(card)) {
                    break;
                }
            }
            return card;
        } else {
            return null;
        }

    }

    /**
     * Teste si la partie est terminée
     *
     * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
     * au moins l'une des deux conditions de fin suivantes est vraie
     * - 3 piles ou plus de la réserve sont vides
     * - la pile de Provinces de la réserve est vide
     * (on suppose que toute partie contient une pile de Provinces, et donc si
     * aucune des piles non-vides de la réserve n'est une pile de Provinces,
     * c'est que la partie est terminée)
     */
    public boolean isFinished() {
        boolean hasProvinceCard = false;
        int nbListesVides = 0;
        for (CardList cardlist : this.supplyStacks) {
            if (cardlist.isEmpty()) {
                nbListesVides += 1;
            }
        }
        if (nbListesVides >= 3) {
            return true;
        }

        this.availableSupplyCards();
        for (Card card : this.availableSupplyCards()) {
            if (card instanceof Province) {
                hasProvinceCard = true;
            }
        }
        return (!hasProvinceCard || nbListesVides >= 3);
    }

    /**
     * Boucle d'exécution d'une partie.
     * <p>
     * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
     * terminée. Lorsque la partie se termine, la méthode affiche le score
     * final et les cartes possédées par chacun des joueurs.
     */
    public void run() {
        while (!this.isFinished()) {
            System.out.println(this.currentPlayerIndex);
            // joue le tour du joueur courant
            this.players[this.currentPlayerIndex].playTurn();
            // passe au joueur suivant
            this.currentPlayerIndex += 1;
            if (this.currentPlayerIndex >= this.players.length) {
                this.currentPlayerIndex = 0;
            }
        }
        System.out.println("Game over.");
        // Affiche le score et les cartes de chaque joueur
        for (int i = 0; i < this.players.length; i++) {
            Player p = this.players[i];
            System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
        }
    }

    /**
     * Lit une ligne de l'entrée standard
     * <p>
     * C'est cette méthode qui doit être appelée à chaque fois qu'on veut lire
     * l'entrée clavier de l'utilisateur (par exemple dans Player.choose), ce
     * qui permet de n'avoir qu'un seul Scanner pour tout le programme
     *
     * @return une chaîne de caractères correspondant à la ligne suivante de
     * l'entrée standard (sans le retour à la ligne final)
     */
    public String readLine() {
        return this.scanner.nextLine();
    }

    public void trashCard(Card card) {
        this.trashedCards.add(card);
    }

    public CardList getLastXCardsFromTrash (int x){
       return  new CardList(this.trashedCards.subList(this.trashedCards.size()-x,this.trashedCards.size()));
    }

    public Card removeFromTrash (String cardName){
        return this.trashedCards.remove(cardName);
    }


}
