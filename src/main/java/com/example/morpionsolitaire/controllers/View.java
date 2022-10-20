package com.example.morpionsolitaire.controllers;

import com.example.morpionsolitaire.views.HelpView;

public enum View {

    MainView("MainPage.fxml"),
    HelpView("HelpView.fxml");

    final static int SCENE_WIDTH = 580;
    final static int SCENE_HEIGHT = 600;

    private String fileName;


    View(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return this.fileName;
    }



}
