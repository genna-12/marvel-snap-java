package com.marvelsnap.view;

import javax.swing.*;
import com.marvelsnap.model.Location;

public class LocationPanel extends JPanel {

    private Location location;
    private JPanel p1CardsArea;
    private JPanel p2CardsArea;
    //Probabilmente servirà infoLabel devo aggiungerla all'UML
    private JLabel infoLabel;
    //Potrebbe anche servire per il controller un locationIndex
    private int locationIndex;

    public LocationPanel(int locIndex) {
        this.locationIndex = locIndex;

        
    }

    public void setLocation(Location loc) {

    }

    public void refresh() {

    }
}