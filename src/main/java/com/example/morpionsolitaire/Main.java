/**
 * The main entry point for the game.
 @author Your Name
 @version 1.0
 @since 1.0
 */

package com.example.morpionsolitaire;

import com.example.morpionsolitaire.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main  {

    /**
        The main method for the game, which launches the main controller.
        @param args The command line arguments.
     */
    public static void main(String[] args) {

        MainController.main(args);
    }
}