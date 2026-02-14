package com.marvelsnap.util;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.model.DebuffCard;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;
import com.marvelsnap.model.Location;

/**
 * Factory class used to create different decks based on the theme selected.
 * It handles the creation of cards with their specific abilities and effects.
 */
public class CardFactory {
    private int id = 0;

    /**
     * Creates a list of cards based on the specified DeckType.
     * Each deck contains a mix of Basic, Bonus, and Debuff cards.
     * 
     * @param type The theme of the deck (AVENGERS, VILLAINS, or XMEN)
     * @return A list of Card objects representing the deck
     */
    public List<Card> createDeck(DeckType type) {
        List<Card> deck = new ArrayList<>();

        switch (type) {
            case AVENGERS:
                /*COST 1*/
                deck.add(new BasicCard(idGenerator(), "Hawkeye", 1, 2, "Davvero hai dei figli?", "Nessuna"));
                deck.add(new BasicCard(idGenerator(), "Wasp", 1, 1, "Le vespe amano le formiche", "Nessuna"));

                /*COST 2*/
                deck.add(new BonusCard(idGenerator(), "Ant-Man", 1, 1, "Non dirgli che è troppo piccolo",
                        "On Reveal: +3 Forza se questa location ha già 3 carte.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        if (loc.getCards(myIdx).size() >= 4)
                            this.addPower(3);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Falcon", 2, 3, "Volare alto", "Nessuna"));

                /*COST 3*/
                deck.add(new BonusCard(idGenerator(), "Captain America", 3, 3, "Il Capitano.",
                        "On Reveal: +1 Forza alle altre tue carte qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        for (Card c : loc.getCards(myIdx))
                            if (c != this)
                                c.addPower(1);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Thor", 3, 4, "Una parola: DAMN", "Nessuna"));

                /*COST 4*/
                deck.add(new BonusCard(7, "Rescue", 4, 4, "Armatura di Pepper.",
                        "On Reveal: +5 Forza se giochi una carta qui il prossimo turno.") {

                    private boolean buffApplied = false;
                    private int playedTurn;
                    private int initialCardCount;

                    @Override
                    public void onReveal(Game game, Location loc) {
                        this.playedTurn = game.getTurnManager().getCurrentTurn();
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();

                        this.initialCardCount = loc.getCards(myIdx).size();

                        GameObserver rescueWatcher = new GameObserver() {

                            @Override
                            public void onGameUpdated() {
                                // If I've already applied the buff or the game is over, do nothing
                                if (buffApplied)
                                    return;

                                int currentTurn = game.getTurnManager().getCurrentTurn();
                                int currentIdx = game.getTurnManager().getCurrentPlayerIndex();

                                /*Let's control if i'm in next turn or in current turn */
                                if (currentTurn == playedTurn + 1 && currentIdx == myIdx) {
                                    /*Let's control if there's a new card */
                                    if (loc.getCards(myIdx).size() > initialCardCount) {
                                        addPower(5);
                                        buffApplied = true;
                                        System.out.println("Rescue: Supporto arrivato! Armatura potenziata (+5).");
                                    }
                                }
                            }

                            @Override
                            public void onTurnChanged(int playerIndex) {
                                if (game.getTurnManager().getCurrentTurn() > playedTurn + 1) {
                                    buffApplied = true;
                                }
                            }

                            @Override
                            public void onGameOver(String winner) {
                            }
                        };

                        game.addObserver(rescueWatcher);
                        System.out.println("Rescue: In attesa di rinforzi per il prossimo turno...");
                    }
                });

                /*COST 5*/
                deck.add(new BonusCard(idGenerator(), "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        this.addPower(loc.calculatePower(myIdx));
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Vision", 5, 7, "Sintetizoide", "Nessuna"));
                deck.add(new BonusCard(idGenerator(), "Black Panther", 5, 4, "Wakanda Forever!",
                        "On Reveal: Raddoppia la forza di questa carta.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        this.addPower(this.getPower());
                    }
                });

                /*COSTO 6*/
                deck.add(new BasicCard(idGenerator(), "Hulk", 6, 12, "HULK SPACCA!", "Nessuna"));
                deck.add(new BonusCard(idGenerator(), "Spectrum", 6, 5, "Energia pura",
                        "On Reveal: +2 a tutte le altre tue carte qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        for (Card c : loc.getCards(myIdx))
                            if (c != this)
                                c.addPower(2);
                    }
                });
                break;
            case VILLAINS:
                /*COST 1 */
                deck.add(new BasicCard(idGenerator(), "Ebony Maw", 1, 7, "Non più furbo di Spidey", "Nessuna"));
                deck.add(new BasicCard(idGenerator(), "Titania", 1, 5, "Forza incredibile", "Nessuna"));
                /*COST 2 */
                deck.add(new BonusCard(idGenerator(), "Carnage", 2, 2, "Caos puro",
                        "On Reveal: +2 Forza per ogni altra tua carta qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        int others = loc.getCards(myIdx).size() - 1;
                        if (others > 0)
                            this.addPower(others * 2);
                    }
                });
                deck.add(new DebuffCard(idGenerator(), "Lizard", 2, 5, "Si è arrabbiato dopo aver perso la coda",
                        "On Reveal: -3 Forza se il nemico ha 4 carte qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        if (loc.getCards(enemyIdx).size() >= 4)
                            this.addPower(-3);
                    }
                });

                /*COST 3*/
                deck.add(new DebuffCard(idGenerator(), "Green Goblin", 3, 1, "Ama la zucca",
                        "On Reveal: -2 a una carta nemica casuale.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        if (!loc.getCards(enemyIdx).isEmpty())
                            loc.getCards(enemyIdx).get(0).addPower(-2);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Electro", 3, 2, "Qualcuno ha un caricatore?", "Nessuna"));

                /*COST 4*/
                deck.add(new BasicCard(idGenerator(), "Crossbones", 4, 8, "Mercenario", "Nessuna"));

                /*COST 5*/
                deck.add(new DebuffCard(idGenerator(), "Spider-Woman", 5, 7, "Veleno bioelettrico",
                        "On Reveal: -1 Forza a tutte le carte nemiche qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        for (Card c : loc.getCards(enemyIdx))
                            c.addPower(-1);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Abomination", 5, 9, "L'orrore", "Nessuna"));
                deck.add(new BasicCard(idGenerator(), "Red Skull", 5, 14, "Rinoplastica", "Nessuna"));

                /*COST 6*/
                deck.add(new BasicCard(idGenerator(), "Ultron", 6, 8, "Intelligenza Suprema", "Nessuna"));
                deck.add(new BasicCard(idGenerator(), "Thanos", 6, 10, "L'ineluttabile.", "Nessuna"));
                break;
            case XMEN:
                /*COST 1*/
                deck.add(new DebuffCard(idGenerator(), "Iceman", 1, 2, "Ghiaccio",
                        "On Reveal: -1 Forza a una carta nemica.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        if (!loc.getCards(enemyIdx).isEmpty())
                            loc.getCards(enemyIdx).get(0).addPower(-1);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Nightcrawler", 1, 2, "BAMF!", "Nessuna"));

                /*COST 2*/
                deck.add(new BonusCard(idGenerator(), "Wolverine", 2, 3, "Non taglia le unghie da un po'",
                        "On Reveal: +3 Forza se giocato al CENTRO.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        if (game.getLocations().indexOf(loc) == 1)
                            this.addPower(3);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Colossus", 2, 3, "Metallo organico", "Nessuna"));

                /*COST 3*/
                deck.add(new BasicCard(idGenerator(), "Cyclops", 3, 4, "Leader", "Nessuna"));
                deck.add(new DebuffCard(idGenerator(), "Storm", 3, 2, "Tempesta",
                        "On Reveal: -2 alla carta nemica più forte qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        Card best = null;
                        for (Card c : loc.getCards(enemyIdx))
                            if (best == null || c.getPower() > best.getPower())
                                best = c;
                        if (best != null)
                            best.addPower(-2);
                    }
                });

                /*COST 4*/
                deck.add(new BonusCard(idGenerator(), "Namor", 4, 6, "Imperius Rex!",
                        "On Reveal: +5 Forza se è la tua unica carta qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int myIdx = game.getTurnManager().getCurrentPlayerIndex();
                        if (loc.getCards(myIdx).size() == 1)
                            this.addPower(5);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Emma Frost", 4, 6, "Regina Bianca", "Nessuna"));

                /*COST 5*/
                deck.add(new BonusCard(idGenerator(), "Professor X", 5, 3, "Controllo mentale",
                        "On Reveal: +5 Forza se il nemico non ha carte qui.") {
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int enemyIdx = (game.getTurnManager().getCurrentPlayerIndex() == 0) ? 1 : 0;
                        if (loc.getCards(enemyIdx).isEmpty())
                            this.addPower(5);
                    }
                });
                deck.add(new BasicCard(idGenerator(), "Beast", 5, 8, "Genio blu", "Nessuna"));

                /*COST 6*/
                deck.add(new BasicCard(idGenerator(), "Magneto", 6, 12, "Signore del magnetismo", "Nessuna"));
                deck.add(new BasicCard(idGenerator(), "Phoenix", 6, 10, "Fenice", "Nessuna"));
                break;
        }
        return deck;
    }

    /**
     * Generates a unique ID for each card.
     * 
     * @return the next available integer ID
     */
    private int idGenerator() {
        return ++id;
    }
}
