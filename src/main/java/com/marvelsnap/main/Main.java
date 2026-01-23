package com.marvelsnap.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.marvelsnap.controller.MainController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        // usiamo invokeLater per progetto grande e sicurezza thread 
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