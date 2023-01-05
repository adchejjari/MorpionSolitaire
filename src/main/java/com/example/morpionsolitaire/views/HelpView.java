/**
 * Handles the event of the user clicking on the "Visit website" hyperlink.
 * Opens the website for the Morpion Solitaire game in the user's default browser.
 *
 * @param actionEvent the action event triggered by the user clicking on the hyperlink
 * @author Adnan Mathuschan
 * @version 1.0
 * @since 2023-01-05
 */

package com.example.morpionsolitaire.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.application.Application;
import javafx.application.HostServices;

public class HelpView{

    public Hyperlink url;

    /**
     * Handles the event of the user clicking on the "Visit website" hyperlink.
     * Opens the website for the Morpion Solitaire game in the user's default browser.
     *
     * @param actionEvent the action event triggered by the user clicking on the hyperlink
     */
    public void visitWebsite(ActionEvent actionEvent) {
        //TODO
        //hostServices.showDocument("www.morpionsolitaire.com");
    }
}
