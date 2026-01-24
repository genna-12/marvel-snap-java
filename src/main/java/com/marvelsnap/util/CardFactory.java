package com.marvelsnap.util;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.model.DebuffCard;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Location;

/* L'idea per questa classe è creare dei mazzi di carte predefiniti (DeckType) per evitare di dover implementare una sezione
   di costruzione del mazzo nell'applicazione, che complicherebbe notevolmente il progetto.
   Inoltre per permettere che ogni carta abbia il suo effetto specifico come su Marvel Snap, andrebbero implementati
   dei metodi specifici per ogni carta, prolly tramite l'uso di anonimous class o lambda expressions.
   
   Per il momento l'idea è questa per evitare di avere 200 classi, una per ogni carta del gioco ma se non dovessi 
   riuscire a implementare quanto scritto sopra, opterò per la soluzione delle 200 classi.
   */

public class CardFactory {
    private int id = 0;

    public List<Card> createDeck(DeckType type) {
        List<Card> deck = new ArrayList<>();
        switch (type) {
            case AVENGERS:
                deck.add(new BasicCard(idGenerator(), "Hawk Eye", 1, 1, "Arciere"));
                // Primo esempio di implementazione dell'effetto della carta tramite classe anonima
                deck.add(new BonusCard(idGenerator(), "Medusa", 2, 2, "Ha appena fatto il balsamo", "+2 forza se al centro"){
                    @Override
                    public void onReveal(Game game, Location loc) {
                        int index = game.getLocations().indexOf(loc);
                        if (index == 1) {
                            this.power += 2;
                            System.out.println("La forza di Medusa è aumentata di 2!");
                        }
                    }
                });
                break;
            case VILLAINS:
                deck.add(new BasicCard(idGenerator(), "Thanos", 5, 5, "Ineluttabile"));
                break;
            case XMEN:
                deck.add(new BasicCard(idGenerator(), "Wolverine", 3, 3, "Non si taglia le unghie da un po'"));
                break;
            default:
                break;
        }
        return deck;
    }

    private int idGenerator() {
        return id++;
    }

    public Card createCard(String id) {
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }
}
