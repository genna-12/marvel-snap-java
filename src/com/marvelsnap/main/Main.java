package com.marvelsnap.main;

import javax.swing.SwingUtilities;

import com.marvelsnap.controller.MainController;

public class Main {
    public static void main(String[] args) {
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