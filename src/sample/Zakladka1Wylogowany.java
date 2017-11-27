package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Zakladka1Wylogowany {

    public TextField loginfield;
    public TextField passwordfield;
    public Label error_label;
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

    public void logIn(ActionEvent actionEvent) {
        String login = loginfield.getText();
        String haslo = passwordfield.getText();

        if (login.equals("")||haslo.equals("")){
            error_label.setText("Wpisz login i/lub hasło.");
        }
        else {
            for (Uzytkownik przybysz:getParentController().getUzytkownicy()){
                if (login.equals(przybysz.getLogin())&&haslo.equals(przybysz.getHaslo())){
                    getParentController().zaloguj(przybysz);
                    getParentController().logowanie(new ActionEvent());
                    break;
                }
            }
            error_label.setText("Zły login lub hasło.");
        }


    }
}
