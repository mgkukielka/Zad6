package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class Zakladka2 {

    public Label pesel_error;
    public TextField imie_txt;
    public TextField nazwisko_txt;
    public TextField wzrost_txt;
    public TextField pesel_txt;
    public TableView<Czlowieczek> table;
    public Label imie_error;
    public Label nazwisko_error;
    public Label wzrost_error;
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

    public void liczStatystyki(ActionEvent actionEvent){
        Integer n=0;
        Double avWiek = 0.0;
        Double avWzrost = 0.0;
        for (Czlowieczek czlowieczek: table.getItems()) {
            avWiek+=czlowieczek.getWiek();
            avWzrost+=czlowieczek.getWzrost();
            n+=1;
        }
        avWiek/=n;
        avWzrost/=n;
        parentController.setStatystyki(new Statystyki(avWzrost,avWiek,n));
        parentController.statystyki(actionEvent);
    }

    public static boolean sprawdzPesel(String s) {
        return s != null && s.matches("(\\d*\\.?\\d+)")&&s.length()==11;
    }

    public void handleClick(ActionEvent actionEvent) {
        Czlowieczek nowy = new Czlowieczek();
        if (imie_txt.getText().equals("")) {
            imie_error.setText("Brak imienia!");

        } else {
            imie_error.setText("");
            nowy.setImie(imie_txt.getText());
        }
        if (nazwisko_txt.getText().equals("")) {
            nazwisko_error.setText("Brak nazwiska!");
        } else{
            nazwisko_error.setText("");
            nowy.setNazwisko(nazwisko_txt.getText());
        }

        if (wzrost_txt.getText().equals("")) {
            wzrost_error.setText("Brak wzrostu!");
        } else {
            wzrost_error.setText("");
            nowy.setWzrost(Integer.parseInt(wzrost_txt.getText()));

        }
        if (pesel_txt.getText().equals("")) {
            pesel_error.setText("Brak peselu!");
        } else if (!sprawdzPesel(pesel_txt.getText())){
                pesel_error.setText("Błędny pesel!");
        } else {
                pesel_error.setText("");
                nowy.setPesel(pesel_txt.getText());
            }
        if (nowy.sprawdz()) {table.getItems().add(nowy);}
        System.out.println(table.getItems());

    }

    public void initialize() {

        // Wymusza numeryczne wejście
        pesel_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                if (pesel_txt.getLength() < 11) {
                    pesel_txt.setStyle("-fx-text-fill: black;");
                    if (!newValue.matches("\\d*")) {
                        pesel_txt.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                } else if (pesel_txt.getLength() == 11) {
                    pesel_txt.setStyle("-fx-text-fill: green;");
                    pesel_error.setText("");
                } else {
                    pesel_txt.setText(newValue.substring(0,11));
                }

            }

        });

        wzrost_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    wzrost_txt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }

        });

        for (TableColumn<Czlowieczek, ?> column : table.getColumns()) {
            if ("imie".equals(column.getId())) {
                TableColumn<Czlowieczek, String> imieCol = (TableColumn<Czlowieczek, String>) column;
                imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
            } else if ("nazwisko".equals(column.getId())) {
                TableColumn<Czlowieczek, String> nazwiskoCol = (TableColumn<Czlowieczek, String>) column;
                nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            } else if ("wiek".equals(column.getId())) {
                TableColumn<Czlowieczek, Integer> wiekCol = (TableColumn<Czlowieczek, Integer>) column;
                wiekCol.setCellValueFactory(new PropertyValueFactory<>("wiek"));
            } else if ("pesel".equals(column.getId())) {
                TableColumn<Czlowieczek, String> peselCol = (TableColumn<Czlowieczek, String>) column;
                peselCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));
            } else if ("wzrost".equals(column.getId())) {
                TableColumn<Czlowieczek, Integer> wzrostCol = (TableColumn<Czlowieczek, Integer>) column;
                wzrostCol.setCellValueFactory(new PropertyValueFactory<>("wzrost"));
            } else if ("plec".equals(column.getId())) {
                TableColumn<Czlowieczek, String> plecCol = (TableColumn<Czlowieczek, String>) column;
                plecCol.setCellValueFactory(new PropertyValueFactory<>("plec"));
            } else if ("data".equals(column.getId())) {
                TableColumn<Czlowieczek, String> dataCol = (TableColumn<Czlowieczek, String>) column;
                dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
            } else if ("usun".equals(column.getId())) {
                TableColumn<Czlowieczek, Czlowieczek> usunCol = (TableColumn<Czlowieczek, Czlowieczek>) column;
                usunCol.setCellValueFactory(
                        param -> new ReadOnlyObjectWrapper<>(param.getValue())
                );
                usunCol.setCellFactory(param -> new TableCell<Czlowieczek, Czlowieczek>() {
                    private final Button deleteButton = new Button("Usuń");

                    @Override
                    protected void updateItem(Czlowieczek person, boolean empty) {
                        super.updateItem(person, empty);

                        if (person == null) {
                            setGraphic(null);
                            return;
                        }

                        setGraphic(deleteButton);
                        deleteButton.setOnAction(
                                event -> getTableView().getItems().remove(person)
                        );
                    }
                });
            }

        }
    }
}

