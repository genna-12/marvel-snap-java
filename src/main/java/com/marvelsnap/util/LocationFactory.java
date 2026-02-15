package com.marvelsnap.util;

import java.util.*;

import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.ReducedCostLocation;
import com.marvelsnap.model.Card;

/**
 * The creator of the three locations used in the game.
 */
public class LocationFactory {

    /**
     * Creates a pool of different locations and it selects randomly three of them.
     * 
     * @return a random list formed by three locations.
     */
    public List<Location> createLocations() {
        List<Location> locations = new ArrayList<>();

        locations = new ArrayList<>();

        locations.add(new NormalLocation("Nidavellir", "Aggiunge +5 di forza alle carte presenti qui", 5, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new NormalLocation("Necrosha", "Rimuove -2 di forza alle carte presenti qui", -2, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new NormalLocation("Xandar", "Aggiunge +1 di forza alle carte presenti qui", 1, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new NormalLocation("Metropoli dei Mostri", "Aggiunge +3 forza alle carte di costo 2 presenti qui", 3, List.of(2)));

        locations.add(new NormalLocation("Zona Negativa", "Rimuove -3 di forza alle carte presenti qui", -3, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new NormalLocation("Rete Fognaria", "Rimuove -1 di forza alle carte presenti qui", -1, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new NormalLocation("Lago Hellas", "Aggiunge +2 di forza alle carte di costo 1 presenti qui", 2, List.of(1)));

        locations.add(new ReducedCostLocation("Campi Elisi", "Riduce di 1 il costo delle carte presenti in mano", 1, List.of(1, 2, 3, 4, 5, 6)));

        locations.add(new ReducedCostLocation("Dimensione dei sogni", "Aumenta di 1 il costo delle carte presenti in mano", -1, List.of(0, 1, 2, 3, 4, 5, 6)));

        locations.add(new ReducedCostLocation("Titano", "Riduce di 1 il costo delle carte di costo 6 presenti in mano", 1, List.of(6)));

        locations.add(new ReducedCostLocation("Utopia", "Riduce di 1 il costo delle carte di costo 3 e 4 presenti in mano", 1, List.of(3, 4)));

        locations.add(new Location("Il Trono Spaziale", "Solo una carta può essere posizionata qui") {
            @Override
            protected void applyEffect(Game game) {
                this.capacity = 1;   
            }
        });

        locations.add(new Location("Sanctum Sanctorum", "Nessun giocatore può giocare carte qui") {
            @Override
            protected void applyEffect(Game game) {
                this.capacity = 0;
            }
        });
        
        locations.add(new Location("Limbo", "In questa partita ci sono 7 turni") {
            @Override
            protected void applyEffect(Game game) {
                game.getTurnManager().setMaxTurns(7);
            }
        });

        locations.add(new Location("Il Mondo Assassino", "All'inizio del quarto turno le carte presenti qui vengono distrutte") {
            @Override
            protected void applyEffect(Game game) {
                
                GameObserver locationObserver = new GameObserver() {
                    private boolean effectCompleted = false;

                    @Override
                    public void onGameOver(String winnerName) {
                    }

                    @Override
                    public void onGameUpdated() {
                        if (game.getTurnManager().getCurrentTurn() == 4 && !this.effectCompleted) {
                            cardsPlayer1.removeAll(cardsPlayer1);
                            cardsPlayer2.removeAll(cardsPlayer2);
                            this.effectCompleted = true;
                        }
                    }

                    @Override 
                    public void onTurnChanged(int playerIndex) { 
                    }
                };
                game.addObserver(locationObserver);
                }
        });
                
        locations.add(new Location("Castello di Zemo", "Scambia di lato le carte posizionate qui") {
            @Override
            protected void applyEffect(Game game) {
                List<Card> temp = List.copyOf(this.cardsPlayer1);
                this.cardsPlayer1.removeAll(this.cardsPlayer1);
                this.cardsPlayer1.addAll(this.cardsPlayer2);
                this.cardsPlayer2 = new ArrayList<>(temp);
            }
        }); 

        locations.add(new Location("Nova Roma", "Pesca una carta") {
            @Override
            protected void applyEffect(Game game) {
                game.getPlayer1().drawCard();
                game.getPlayer2().drawCard();
            }
        });

        locations.add(new Location("Olympia", "Pesca due carte") {
            @Override
            protected void applyEffect(Game game) {
                for (int i = 0; i < 2; i++) {
                    game.getPlayer1().drawCard();
                    game.getPlayer2().drawCard();
                }
            }
        });

        locations.add(new Location("Officina del Riparatore", "+1 energia durante questo turno") {
            @Override
            protected void applyEffect(Game game) {
                game.getPlayer1().resetEnergy(game.getPlayer1().getCurrentEnergy() + 1);
                game.getPlayer2().resetEnergy(game.getPlayer2().getCurrentEnergy() + 1);
            }
        });

        locations.add(new Location("Progetto Pegasus", "+5 energia durante questo turno") {
            @Override
            protected void applyEffect(Game game) {
                game.getPlayer1().resetEnergy(game.getPlayer1().getCurrentEnergy() + 5);
                game.getPlayer2().resetEnergy(game.getPlayer2().getCurrentEnergy() + 5);
            }
        });

        Collections.shuffle(locations);
        
        return new ArrayList<>(locations.subList(0, 3));
    }
}
