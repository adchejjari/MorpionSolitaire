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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainController extends Application implements MainView.MainViewListener{

    private GameController gameController;

    private LeaderBoardController leaderBoardController;

    private MainView mainView;

    public MainController() throws IOException {

    }

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

    @Override
    public void setHomePage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.HomeView);
        mainView.add((Parent) loader.load());
    }

    @Override
    public void setNewGamePage() throws IOException, SQLException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.GameBoardView);
        mainView.add((Parent) loader.load());
        gameController = new GameController(loader.getController());
    }

    @Override
    public void setSettingsPage() {
        // TODO : Delete this method
    }

    @Override
    public void setLeaderBoardPage() throws IOException, SQLException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.RankingView);
        mainView.add((Parent) loader.load());
        leaderBoardController = new LeaderBoardController(loader.getController());

    }

    @Override
    public void setHelpPage() throws IOException {
        mainView.clear();
        FXMLLoader loader = ViewSwitcher.load(View.HelpView);
        mainView.add((Parent) loader.load());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.setupStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
