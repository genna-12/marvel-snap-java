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
        setLayout(new GridLayout(1, 3, 10, 0));
        setBackground(new Color(30, 30, 30)); 

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.locationPanels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LocationPanel newLocation = new LocationPanel(i);
            this.locationPanels.add(newLocation);
            this.add(newLocation);
        }
    }

    public void refresh(List<Location> locations, int viewerIdx) {
        if (locations == null || locations.size() < 3) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            this.locationPanels.get(i).setLocation(locations.get(i));
            this.locationPanels.get(i).refresh(viewerIdx);

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

    /*Resets the board */
    public void reset() {
        for (LocationPanel lp : locationPanels) {
            lp.reset();
        }
    }
}