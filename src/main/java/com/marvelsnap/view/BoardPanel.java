package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.marvelsnap.model.Location;
import com.marvelsnap.controller.GameController;

public class BoardPanel extends JPanel {

    private List<LocationPanel> locationPanels;

    public BoardPanel() {
        // finche non implementate la classe scrivo il seguente codice di debug
        setLayout(new GridLayout(1, 3, 10, 0));
        setBackground(new Color(30, 30, 30)); // Colore di sfondo scuro è piu smooth

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.locationPanels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LocationPanel newLocation = new LocationPanel(i);
            this.locationPanels.add(newLocation);
            this.add(newLocation);
        }
    }

    public void refresh(List<Location> locations) {
        if(locations == null || locations.size() < 3) return;
        for (int i = 0; i < 3; i++) {
            this.locationPanels.get(i).setLocation(locations.get(i));
            this.locationPanels.get(i).refresh();

        }
    }

    public List<LocationPanel> getLocationPanels() {
        return this.locationPanels;
    }

    public void setController(GameController controller) {
        for (LocationPanel lp : locationPanels) {
            lp.setController(controller);
        }
    }
}