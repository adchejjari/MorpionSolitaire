/**
 The MainController class serves as the main controller for the application. It handles
 the loading and switching of different views, as well as creating and managing a
 GameController instance when a new game is started.
 @author Your Name
 @version 1.0
 @since 2020-09-01
 */
package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.db.ScoreDataAccessObject;
import com.example.morpionsolitaire.models.Score;
import com.example.morpionsolitaire.utils.View;
import com.example.morpionsolitaire.utils.ViewSwitcher;
import com.example.morpionsolitaire.views.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainController extends Application implements MainView.MainViewListener{

    private GameController gameController;

    private MainView mainView;

    public MainController() throws IOException {

    }
    /**
     * This method sets up the stage for the application, including loading the main view
     * and setting it as the scene for the stage.
     *
     * @param stage The stage to be set up for the application.
     * @throws IOException If there is an error loading the main view.
     */
    public void setupStage(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane(), View.SCENE_HEIGHT, View.SCENE_WIDTH);
        ViewSwitcher.setScene(scene);
        FXMLLoader loader = ViewSwitcher.load(View.MainView);
        ViewSwitcher.setScene((Parent) loader.load());
        mainView = loader.getController();
        mainView.setMainViewListener(this);
        stage.setTitle("Morpion Solitaire");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method is called when the home page button is clicked. It clears the main view
     * and loads the home view into it.
     *
     * @throws IOException If there is an error loading the home view.
     */
    @Override
    public void setHomePage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.HomeView);
        mainView.add((Parent) loader.load());
    }

    /**
     * This method is called when the new game button is clicked. It clears the main view
     * and loads the game board view into it, as well as creating a new GameController instance.
     *
     * @throws IOException If there is an error loading the game board view.
     */
    @Override
    public void setNewGamePage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.GameBoardView);
        mainView.add((Parent) loader.load());
        gameController = new GameController(loader.getController());
    }

    /**
     * This method is called when the settings button is clicked. It clears the main view
     * and loads the settings view into it.
     */
    @Override
    public void setSettingsPage() {
        //mainView.clear();
        //TilePane view = (TilePane) ViewSwitcher.switchTo(View.);
        //mainView.add(view);
    }

    /**
     * This method is called when the ranking button is clicked. It clears the main view
     * and loads the ranking view into it.
     *
     * @throws IOException If there is an error loading the ranking view.
     */
    @Override
    public void setRankingPage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.RankingView);
        mainView.add((Parent) loader.load());
    }

    /**
     * This method is called when the help button is clicked. It clears the main view
     * and loads the help view into it.
     *
     * @throws IOException If there is an error loading the help view.
     */
    @Override
    public void setHelpPage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.HelpView);
        mainView.add((Parent) loader.load());
    }

    /**
     * This method is called to start the application. It sets up the stage for the application.
     *
     * @param stage The stage to be set up for the application.
     * @throws Exception If there is an error setting up the stage.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.setupStage(stage);
    }

    /**
     * The main method for the application. It launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
