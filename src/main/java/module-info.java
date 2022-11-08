module com.example.morpionsolitaire {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;


    opens com.example.morpionsolitaire to javafx.fxml, javafx.graphics;
    exports com.example.morpionsolitaire;
    opens com.example.morpionsolitaire.controllers to javafx.fxml, javafx.graphics;
    exports com.example.morpionsolitaire.views;
    opens com.example.morpionsolitaire.views to javafx.fxml, javafx.graphics;
}

