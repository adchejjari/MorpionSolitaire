package com.example.morpionsolitaire.controllers;


public enum View {

    MainView("MainPage.fxml"),
    HelpView("HelpView.fxml"),
    GameBoardView("GameBoardView.fxml"),
    RankingView("RankingView.fxml");


    final static int SCENE_WIDTH = 680;
    final static int SCENE_HEIGHT = 680;

    private String fileName;


    View(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return this.fileName;
    }



}
