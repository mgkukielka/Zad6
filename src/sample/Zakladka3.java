package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;

public class Zakladka3 {
    private Zakladki parentController;
    public GridPane panel;
    @FXML
    private Label lbl_avWzrost;
    @FXML
    private Label lbl_avWiek;
    @FXML
    private Label lbl_liczba;
    public Pane brak;

    public void drukujStatystyki(){
        if (parentController.getStatystyki()!=null) {
            DecimalFormat df = new DecimalFormat("#.00");

            lbl_avWiek.setText(df.format(parentController.getStatystyki().getSredniWiek()).toString());
            lbl_avWzrost.setText(df.format(parentController.getStatystyki().getSredniWzrost()).toString());
            lbl_liczba.setText(parentController.getStatystyki().getLiczbaOsob().toString());
            panel.setVisible(true);
            brak.setVisible(false);
        } else {
            panel.setVisible(false);
            brak.setVisible(true);
        }
    }
    public void zmienPlansze(ActionEvent actionEvent) {
        this.drukujStatystyki();
        parentController.statystyki(actionEvent);
    }
    public void setParentController(Zakladki parentController) {
        this.parentController = parentController;
    }

    public Zakladki getParentController() {
        return parentController;
    }

}

