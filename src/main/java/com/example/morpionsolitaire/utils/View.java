/**
 * Enum representing different views in a JavaFX application. Each view has a corresponding .fxml file
 * that defines the layout of the view.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.utils;

public enum View {

    MainView("MainPage.fxml"),
    HomeView("HomeView.fxml"),
    HelpView("HelpView.fxml"),
    GameBoardView("GameBoardView.fxml"),
    RankingView("RankingView.fxml");


    public final static int SCENE_WIDTH = 920;
    public final static int SCENE_HEIGHT = 680;

    private String fileName;

    /**
     * Constructs a new View with the given .fxml file name.
     * @param fileName the name of the .fxml file associated with the view
     */
    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the name of the .fxml file associated with the view.
     * @return the name of the .fxml file associated with the view
     */
    public String getFileName() {
        return this.fileName;
    }

}
