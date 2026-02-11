package com.marvelsnap.model;

import java.util.*;

public class NormalLocation extends Location {

    public NormalLocation(String name, String description) {
        super(name, description);
    }

    public void applyEffect(Game game) {
        //Lascialo vuoto fai override in locationfactory nelle location con effetti speciali
    }
}
