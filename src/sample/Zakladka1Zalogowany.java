package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Zakladka1Zalogowany {


    private Zakladki parentController;

    public void zmienPlansze(ActionEvent actionEvent) {
        parentController.logowanie(actionEvent);
    }

    public void setParentController(Zakladki parentController) {
        this.parentController = parentController;
    }

    public Zakladki getParentController() {
        return parentController;
    }

    public void logOut(ActionEvent actionEvent) {
        getParentController().wyloguj();
        getParentController().logowanie(new ActionEvent());
    }
}
