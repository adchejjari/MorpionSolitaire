package com.example.morpionsolitaire.utils;


public enum View {

    MainView("MainPage.fxml"),
    HomeView("HomeView.fxml"),
    HelpView("HelpView.fxml"),
    GameBoardView("GameBoardView.fxml"),
    RankingView("RankingView.fxml");


    public final static int SCENE_WIDTH = 680;
    public final static int SCENE_HEIGHT = 680;

    private String fileName;


    View(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return this.fileName;
    }

}
