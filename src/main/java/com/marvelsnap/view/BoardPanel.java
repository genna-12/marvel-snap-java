package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.marvelsnap.model.Location;

public class BoardPanel extends JPanel {

    private List<LocationPanel> locationPanels;

    public BoardPanel() {
        // finche non implementate la classe scrivo il seguente codice di debug
        setLayout(new GridLayout(1, 3));
        setBackground(new Color(100, 50, 50));
        setPreferredSize(new Dimension(800, 400));

        this.locationPanels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LocationPanel newLocation = new LocationPanel(i);
            this.locationPanels.add(newLocation);
            newLocation.setBorder(BorderFactory.createLineBorder(Color.GRAY)); 
            this.add(newLocation);
        }
    }

    public void refresh(List<Location> locations) {
        for (int i = 0; i < 3; i++) {
            this.locationPanels.get(i).setLocation(locations.get(i));
            this.locationPanels.get(i).refresh();
        }
    }
}