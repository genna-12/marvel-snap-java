package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.marvelsnap.model.Location;
import com.marvelsnap.controller.GameController;

/**
 * The main board, that contains and updates the graphical side of the locations.
 */
public class BoardPanel extends JPanel {

    private List<LocationPanel> locationPanels;

    /**
     * The class constructor.
     */
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

    /**
     * Updates the state of all the three locations.
     * 
     * @param locations the three locations of the current game.
     * @param viewerIdx the index of the current player.
     */
    public void refresh(List<Location> locations, int viewerIdx) {
        if (locations == null || locations.size() < 3) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            this.locationPanels.get(i).setLocation(locations.get(i));
            this.locationPanels.get(i).refresh(viewerIdx);

        }
    }

    /**
     * Gets the three LocationPanels.
     * 
     * @return a list formed by the three LocationPanels.
     */
    public List<LocationPanel> getLocationPanels() {
        return this.locationPanels;
    }

    /**
     * Sets the controller for all the three locations.
     * 
     * @param controller the chosen controller.
     */
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