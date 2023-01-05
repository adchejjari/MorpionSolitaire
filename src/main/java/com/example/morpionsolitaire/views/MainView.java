/**
 * A JavaFX view for the main menu of an application. The main menu
 * has a navigation bar with several buttons that allow the user to
 * navigate to different pages or views in the application.
 *
 * @author Adnan Mathuschan
 * @version 1.0
 * @since 2023-01-05
 */

package com.example.morpionsolitaire.views;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class MainView {

    final private static String PRESSED_BUTTON_STYLE = "pressed-button";
    private boolean menuDisplayed = true;
    private Button toggledButton;
    @FXML
    private VBox navigationBar;
    @FXML
    private Button menuAnimationButton;
    @FXML
    private Image downArrowImage;
    @FXML
    private Image upArrowImage;
    @FXML
    private StackPane mainFrame;
    @FXML
    private Button newGameButton;
    @FXML
    private Button rankingButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button homeButton;

    MainViewListener mainViewListener;

    /**
     * Initializes the MainView by setting the toggledButton field to the homeButton and adding the PRESSED_BUTTON_STYLE style class to the homeButton.
     */
    @FXML
    private void initialize() {
        this.toggledButton = homeButton;
        replacePressedButton(homeButton);
    }

    /**
     * Animates the navigation bar by sliding it up or down. If the navigation bar is currently displayed, the method will slide it up and hide it.
     * If the navigation bar is currently hidden, the method will slide it down and display it.
     */
    public void handleNavbar() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode(this.navigationBar);
        if (menuDisplayed) {
            translateTransition.setToY(-50);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                this.menuAnimationButton.setGraphic(new ImageView(this.downArrowImage));
                this.menuDisplayed = false;
            });
        }else{
            translateTransition.setToY(0);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                this.menuAnimationButton.setGraphic(new ImageView(this.upArrowImage));
                this.menuDisplayed = true;
            });
        }
    }

    /**
     * Applies a fade animation to the given node.
     * @param node The node to which the fade animation should be applied.
     */
    private void playFadeAnimation(Node node){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1500));
        fade.setFromValue(0);
        fade.setToValue(0.5);
        fade.setAutoReverse(true);
        fade.setNode(node);
        fade.play();
    }

    /**
     * Sets the MainViewListener for the MainView.
     * @param mvl The MainViewListener to set for the MainView.
     */
    public void setMainViewListener(MainViewListener mvl){
        this.mainViewListener = mvl;
    }

    /**
     * Clears the main frame by removing all content from it.
     */
    public void clear(){
        mainFrame.getChildren().clear();
    }

    /**
     * Adds the given node to the main frame.
     * @param node The node to add to the main frame.
     */
    public void add(Node node){
        mainFrame.getChildren().add(node);
    }

    /**
     * Event handler for the new game button. Uses the mainViewListener to navigate to the new game page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void onNewGameButtonClick() throws IOException {
        this.mainViewListener.setNewGamePage();
        replacePressedButton(newGameButton);
    }

    /**
     * Event handler for the help button. Uses the mainViewListener to navigate to the help page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void onHelpButtonClick() throws IOException {
        this.mainViewListener.setHelpPage();
        replacePressedButton(helpButton);

    }

    /**
     * Event handler for the setting button. Clears the main frame.
     */
    @FXML
    private void onSettingButtonClick() {
        mainFrame.getChildren().clear();
        replacePressedButton(settingButton);
    }

    /**
     * Event handler for the ranking button. Uses the mainViewListener to navigate to the ranking page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void onRankingButtonClick() throws IOException {
        this.mainViewListener.setRankingPage();
        replacePressedButton(rankingButton);
    }

    /**
     * Event handler for the home button. Uses the mainViewListener to navigate to the home page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void onHomeButtonClick() throws IOException {
        this.mainViewListener.setHomePage();
        replacePressedButton(homeButton);
    }

    /**
     * Replaces the pressed button with the given button by removing the PRESSED_BUTTON_STYLE style class from the toggledButton and adding it to the given button.
     * @param button The button to make the pressed button.
     */
    private void replacePressedButton(Button button){
        toggledButton.getStyleClass().remove(PRESSED_BUTTON_STYLE);
        button.getStyleClass().add(PRESSED_BUTTON_STYLE);
        toggledButton = button;
    }

    /**
     * The listener interface for receiving main view events.
     * The class that is interested in processing a main view event
     * implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addMainViewListener<code> method. When
     * the main view event occurs, that object's appropriate
     * method is invoked.
     * @see MainViewEvent
     */
    public interface MainViewListener{

        /**
         Sets the home page.
         @throws IOException Signals that an I/O exception has occurred.
         */
        void setHomePage() throws IOException;

        /**

         Sets the new game page.
         @throws IOException Signals that an I/O exception has occurred.
         */
        void setNewGamePage() throws IOException;

        void setSettingsPage();

        /**

         Sets the ranking page.
         @throws IOException Signals that an I/O exception has occurred.
         */
        void setRankingPage() throws IOException;

        /**

         Sets the Help page.
         @throws IOException Signals that an I/O exception has occurred.
         */
        void setHelpPage() throws IOException;
    }
}