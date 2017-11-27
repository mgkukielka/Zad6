package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Zakladka3 {
    private Zakladki parentController;
    @FXML
    private Label lbl_avWzrost;
    @FXML
    private Label lbl_avWiek;
    @FXML
    private Label lbl_liczba;

    public void drukujStatystyki(){
        if (parentController.getStatystyki()!=null) {
            lbl_avWiek.setText(parentController.getStatystyki().getSredniWiek().toString());
            lbl_avWzrost.setText(parentController.getStatystyki().getSredniWzrost().toString());
            lbl_liczba.setText(parentController.getStatystyki().getLiczbaOsob().toString());
        } else {
            System.out.println("Nie ma nic");
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

