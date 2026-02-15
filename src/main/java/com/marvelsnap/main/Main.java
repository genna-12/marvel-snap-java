package com.marvelsnap.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.marvelsnap.controller.MainController;

import javax.swing.SwingUtilities;

/**
 * Main class that serves as the entry point for the Marvel Snap application.
 * It sets up the UI theme and starts the main controller.
 */
public class Main {

    /**
     * Main method to launch the game.
     * Uses SwingUtilities to ensure the GUI is created on the Event Dispatch Thread.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        /*We use invokeLater because of thread security*/ 
        SwingUtilities.invokeLater(() -> {
            try{
                MainController controller = new MainController();
                controller.startApp();                                                    
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}