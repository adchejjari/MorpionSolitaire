package com.example.morpionsolitaire.views;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainView {

    @FXML
    public VBox navigationBar;

    @FXML
    public Button menuAnimationButton;

    @FXML
    public Image downArrowImage;
    @FXML
    public Image upArrowImage;

    public boolean menuDisplayed = true;

    public Pane mainFrame;



    public void handleNavbar() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode(this.navigationBar);
        if (menuDisplayed) {
            translateTransition.setToY(-50);
            this.animateMain();
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

    public void animateMain(){
        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(mainFrame.translateXProperty(), 0, Interpolator.EASE_IN);
        //mainFrame.setScaleY(1.3);
        KeyFrame kf = new KeyFrame(Duration.seconds(3), kv);
        tl.getKeyFrames().add(kf);
        tl.play();
    }

    public void onNewGameButtonClick(ActionEvent actionEvent) {
    }

    public void onHelpButtonClick(ActionEvent actionEvent) {
    }

    public void onSettingButtonClick(ActionEvent actionEvent) {
    }

    public void onRankingButtonClick(ActionEvent actionEvent) {
    }


    public interface PageSwitcher{
        void showSettings();
        void showHelp();
    };
}