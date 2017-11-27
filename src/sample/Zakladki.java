package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class Zakladki {
    @FXML
    private Pane glowna;
    @FXML
    private Uzytkownik zalogowany;
    @FXML
    private Label lbl_rola;
    @FXML
    private Label lbl_zalogowany;
    private ArrayList<Uzytkownik> uzytkownicy = new ArrayList<>();
    private Statystyki stats;

    public Uzytkownik getZalogowany() {
        return zalogowany;
    }

    public ArrayList<Uzytkownik> getUzytkownicy() {
        return uzytkownicy;
    }

    public void zaloguj(Uzytkownik uzytkownik) {
        this.zalogowany=uzytkownik;
        lbl_zalogowany.setText(uzytkownik.getLogin());
        lbl_rola.setText(uzytkownik.getRola());
    }
    public void wyloguj() {
        this.zalogowany=null;
        lbl_zalogowany.setText("");
        lbl_rola.setText("");
    }

    public void logowanie(ActionEvent actionEvent) {

        if(zalogowany == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logowanie.fxml"));
            try {
                Parent pane = loader.load();
                Zakladka1Wylogowany cntr = loader.getController();
                cntr.setParentController(this);
                glowna.getChildren().clear();
                glowna.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("wylogowywanie.fxml"));
            try {
                Parent pane = loader.load();
                Zakladka1Zalogowany cntr = loader.getController();
                cntr.setParentController(this);
                glowna.getChildren().clear();
                glowna.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dane(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dane.fxml"));
        try {
            Parent pane = loader.load();
            Zakladka2 cntr = loader.getController();
            cntr.setParentController(this);
            glowna.getChildren().clear();
            glowna.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void statystyki(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("statystyki.fxml"));
        try {
            Parent pane = loader.load();
            Zakladka3 cntr = loader.getController();
            cntr.setParentController(this);
            cntr.drukujStatystyki();
            glowna.getChildren().clear();
            glowna.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(){

        Uzytkownik stefan = new Uzytkownik("Stefan","admin1","admin");
        Uzytkownik krzysio = new Uzytkownik("Krzysio","haslo123","uzytkownik");
        uzytkownicy.add(stefan);
        uzytkownicy.add(krzysio);
        this.zaloguj(stefan);
    }
    public void setStatystyki(Statystyki stats) {
        this.stats=stats;

    }

    public Statystyki getStatystyki() {
        return stats;
    }

}
