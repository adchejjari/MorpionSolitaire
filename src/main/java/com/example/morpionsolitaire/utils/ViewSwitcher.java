/**
 * ViewSwitcher is a utility class for switching between views in a JavaFX application.
 * It contains a static {@link Scene} object that can be set using the {@link #setScene(Scene)} method.
 * The {@link #load(View)} method can be used to load an FXML file and return an {@link FXMLLoader} object.
 * The {@link #setScene(Parent)} method can be used to set the root node of the static Scene object.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.utils;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class ViewSwitcher {

    private static Scene scene;

    public static FXMLLoader loader;

    /**
     * Sets the static Scene object.
     * @param scene the Scene object to set
     */
    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    /**
     * Loads an FXML file and returns an FXMLLoader object.
     * @param view the View object representing the FXML file to load
     * @return an FXMLLoader object
     */
    public static FXMLLoader load(View view){
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(Main.class.getResource(view.getFileName()))
        );
        return loader;
    }

    /**
     * Sets the root node of the static Scene object.
     * @param r the root node to set
     */
    public static void setScene(Parent r){
        scene.setRoot(r);
    }
}
