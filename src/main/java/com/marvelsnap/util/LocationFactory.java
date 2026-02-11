package com.marvelsnap.util;

import java.util.*;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Card;

public class LocationFactory {

    public List<Location> createLocations() {
        List<Location> locations = new ArrayList<>();
        
        // creo un po di location per far funzionare il codice, aggiungi quante ne vuoi e anche gli effetti speciali
        // ora non ho voglia di mettermi a fare le cose di ognuno
        // per gli effetti crea classi anonime e fai override di applyEffect, ti lascio l'esempio sotto
        locations.add(new NormalLocation("Xandar", "aggiunge 2 di potere a tutte le carte che costano 3") {
            @Override
            public void applyEffect(Game game) {
                for (Card c : this.cardsPlayer1) {
                    if (c.getCost() == 3) {
                        c.addPower(2);
                    }
                }
                for (Card c : this.cardsPlayer2) {
                    if (c.getCost() == 3) {
                        c.addPower(2);
                    }
                }
            }
        });
        locations.add(new NormalLocation("Wakanda", "Nessun effetto."));
        locations.add(new NormalLocation("Stark Tower", "Nessun effetto."));
        locations.add(new NormalLocation("Atlantis", "Nessun effetto."));
        locations.add(new NormalLocation("Hell's Kitchen", "Nessun effetto."));

        // mischio le location e ne prendo 3
        Collections.shuffle(locations);
        return new ArrayList<>(locations.subList(0, 3));
    }
}
