package test;

import dominion.card.base.*;
import dominion.card.common.*;

public class TestCards extends Test {

	private static void testCellar(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Cellar.class, 2);
		p1.addToHand(Estate.class, 3);
		p1.addToDraw(Copper.class, 2);
		g.setInput("Estate\nEstate\n\n");
		p1.playCard("Cellar");
		t.check(p1.getActions() == 1);
		t.check(hasCards(p1.hand, "Cellar, Copper, Copper, Estate"));
		t.check(hasCards(p1.discard, "Estate, Estate"));
	}

	private static void testChapel(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Chapel.class, 2);
		p1.addToHand(Estate.class, 1);
		p1.addToHand(Copper.class, 2);
		g.setInput("Estate\nCopper\n\n");
		p1.playCard("Chapel");
		t.check(hasCards(p1.hand, "Chapel, Copper"));
		t.check(p1.discard.isEmpty());
	}

	private static void testMoat(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Moat.class, 1);
		p1.addToDraw(Copper.class, 3);
		p1.playCard("Moat");
		t.check(hasCards(p1.hand, "Copper, Copper"));
		t.check(hasCards(p1.draw, "Copper"));
	}

	private static void testChancellor(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Chancellor.class, 2);
		p1.addToDraw(Estate.class, 3);
		g.setInput("n\ny\n");
		p1.playCard("Chancellor");
		t.check(p1.getMoney() == 2);
		t.check(hasCards(p1.draw, "Estate, Estate"));
		t.check(p1.discard.size() == 0);
		p1.playCard("Chancellor");
		t.check(p1.getMoney() == 4);
		t.check(hasCards(p1.discard, "Estate, Estate"));
		t.check(p1.draw.size() == 0);
	}
		
	private static void testVillage(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Village.class, 2);
		p1.addToHand(Copper.class, 1);
		p1.addToDraw(Estate.class, 1);
		int actions = p1.getActions();
		p1.playCard("Village");
		t.check(p1.getActions() == actions + 2);
		t.check(hasCards(p1.hand, "Copper, Estate, Village"));
	}

	private static void testWoodcutter(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Woodcutter.class, 1);
		p1.playCard("Woodcutter");
		t.check(p1.getBuys() == 1);
		t.check(p1.getMoney() == 2);
	}

	private static void testWorkshop(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Workshop.class, 1);
		g.setInput("Gold\nSilver\n");
		p1.playCard("Workshop");
		t.check(hasCards(p1.discard, "Silver"));
	}

	private static void testBureaucrat(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		p0.clear();
		p0.addToHand(Estate.class, 1);
		p0.addToHand(Duchy.class, 1);
		p1.clear();
		p1.addToHand(Bureaucrat.class, 1);
		p2.clear();
		p2.addToHand(Gold.class, 2);
		p2.addToHand(Copper.class, 2);
		g.setInput("Province\nDuchy\n\n");
		p1.playCard("Bureaucrat");
		t.check(hasCards(p0.hand, "Estate"));
		t.check(hasCards(p0.draw, "Duchy"));
		t.check(p1.discard.isEmpty());
		t.check(hasCards(p1.draw, "Silver"));
		t.check(p2.draw.isEmpty());
	}

	private static void testGardens(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Gardens.class, 1);
		t.check(p1.hand.get(0).victoryValue(p1.player) == 0);
		p1.addToDiscard(Silver.class, 5);
		p1.addToHand(Gardens.class, 3);
		// 9 cartes
		t.check(p1.hand.get(0).victoryValue(p1.player) == 0);
		p1.draw.add(new Copper());
		// 10 cartes
		t.check(p1.hand.get(0).victoryValue(p1.player) == 1);
		p1.addToDraw(Copper.class, 30);
		// 40 cartes
		t.check(p1.hand.get(0).victoryValue(p1.player) == 4);
	}
		
	private static void testFeast(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Feast.class, 1);
		g.setInput("Gold\nDuchy\n");
		p1.playCard("Feast");
		t.check(p1.draw.isEmpty());
		t.check(p1.hand.isEmpty());
		t.check(hasCards(p1.discard, "Duchy"));
	}

	private static void testMoneylender(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Moneylender.class, 2);
		p1.addToHand(Silver.class, 2);
		p1.addToHand(Copper.class, 1);
		// avec Copper
		p1.playCard("Moneylender");
		t.check(p1.getMoney() == 3);
		// sans Copper
		p1.playCard("Moneylender");
		t.check(p1.getMoney() == 3);
		t.check(p1.discard.size() == 0);
		t.check(hasCards(p1.hand, "Silver, Silver"));
	}

	private static void testSmithy(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		p1.clear();
		p1.addToHand(Smithy.class, 3);
		p1.addToHand(Copper.class, 2);
		p1.addToDraw(Estate.class, 3);
		p1.addToDiscard(Silver.class, 4);
		p1.playCard("Smithy");
		t.check(p1.hand.size() == 7);
		t.check(p1.draw.size() == 0);
		t.check(p1.discard.size() == 4);
		p1.playCard("Smithy");
		t.check(p1.hand.size() == 9);
		t.check(p1.draw.size() == 1);
		t.check(p1.discard.size() == 0);
		p1.playCard("Smithy");
		t.check(p1.hand.size() == 9);
		t.check(p1.draw.size() == 0);
		t.check(p1.discard.size() == 0);
	}

	private static void testCouncilRoom(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		p0.clear();
		p0.addToDraw(Copper.class, 3);
		p1.clear();
		p1.addToHand(CouncilRoom.class, 1);
		p1.addToDraw(CouncilRoom.class, 2);
		p1.addToDraw(Estate.class, 4);
		p2.clear();
		p2.addToDiscard(Estate.class, 1);
		p1.playCard("Council Room");
		t.check(p0.hand.size() == 1);
		t.check(p1.hand.size() == 4);
		t.check(p2.hand.size() == 1);
		p1.playCard("Council Room");
		t.check(p0.hand.size() == 2);
		t.check(p1.hand.size() == 5);
		t.check(p2.hand.size() == 1);
		p1.playCard("Council Room");
		t.check(p0.hand.size() == 3);
		t.check(p1.hand.size() == 4);
		t.check(p2.hand.size() == 1);
	}

	private static void testWitch(Test t) {
		GameProxy g = new GameProxy(minimalGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		p0.clear();
		p1.clear();
		p1.addToHand(Witch.class, 2);
		p1.addToDiscard(Copper.class, 3);
		p2.clear();
		p1.playCard("Witch");
		t.check(p1.hand.size() == 3);
		t.check(p1.draw.size() == 1);
		t.check(hasCards(p0.discard, "Curse"));
		t.check(hasCards(p2.discard, "Curse"));
		p1.playCard("Witch");
		t.check(p1.hand.size() == 3);
		t.check(p1.draw.size() == 0);
		t.check(hasCards(p0.discard, "Curse, Curse"));
		t.check(hasCards(p2.discard, "Curse, Curse"));
	}

	public void run() {
		this.runTest("Cellar", TestCards::testCellar);
		this.runTest("Chapel", TestCards::testChapel);
		this.runTest("Moat", TestCards::testMoat);
		this.runTest("Chancellor", TestCards::testChancellor);
		this.runTest("Village", TestCards::testVillage);
		this.runTest("Woodcutter", TestCards::testWoodcutter);
		this.runTest("Workshop", TestCards::testWorkshop);
		this.runTest("Bureaucrat", TestCards::testBureaucrat);
		this.runTest("Gardens", TestCards::testGardens);
		this.runTest("Feast", TestCards::testFeast);
		this.runTest("Moneylender", TestCards::testMoneylender);
		this.runTest("Smithy", TestCards::testSmithy);
		this.runTest("Council Room", TestCards::testCouncilRoom);
		this.runTest("Witch", TestCards::testWitch);
	}

	public static void main(String[] args) {
		TestCards t = new TestCards();
		t.run();
		System.out.println("----");
		System.out.println(t);
	}
}