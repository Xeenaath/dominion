import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;


/**
 * Classe pour l'exécution d'une partie de Dominion
 */
class Main{
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le
		// tableau)
		String[] playerNames = new String[]{"Marco", "Polo"};
		// Prépare les piles "royaume" de la réserve (hors cartes communes)
		List<CardList> kingdomStacks = new ArrayList<CardList>();
		CardList stackVillage, stackMarket, stackCellar,stackMilitia,stackMine,stackMoat,stackRemodel,stackSmithy,stackWoodcutter,stackWorkshop;
		// Ajouter un bloc pour chaque carte royaume utiliser
		stackVillage = new CardList();
		stackMarket = new CardList();
		stackCellar = new CardList();
		stackMilitia = new CardList();
		stackMine = new CardList();
		stackMoat = new CardList();
		stackRemodel = new CardList();
		stackSmithy = new CardList();
		stackWoodcutter = new CardList();
		stackWorkshop = new CardList();

		for (int i = 0; i < 10; i++) {
			stackVillage.add(new Village());
			stackMarket.add(new Market());
			stackCellar.add(new Cellar());
			stackMilitia.add(new Militia());
			stackMine.add(new Mine());
			stackMoat.add(new Moat());
			stackRemodel.add(new Remodel());
			stackSmithy.add(new Smithy());
			stackWoodcutter.add(new Woodcutter());
			stackWorkshop.add(new Workshop());
		}

		kingdomStacks.add(stackVillage);
		kingdomStacks.add(stackMarket);
		kingdomStacks.add(stackCellar);
		kingdomStacks.add(stackMilitia);
		kingdomStacks.add(stackMine);
		kingdomStacks.add(stackMoat);
		kingdomStacks.add(stackRemodel);
		kingdomStacks.add(stackSmithy);
		kingdomStacks.add(stackWoodcutter);
		kingdomStacks.add(stackWorkshop);
		// Instancie et execute une partie
		Game g = new Game(playerNames, kingdomStacks);
		g.run();
	}
}