package sample;

import java.time.LocalDate;
import java.time.Period;

public class Czlowieczek {

    protected String imie;
    protected String nazwisko;
    protected Integer wiek;
    protected String pesel;
    protected Integer wzrost;
    protected String data;
    protected String plec;

    public Czlowieczek() {

    }
    public boolean sprawdz() {
        return pesel!=null&&imie!=null&&nazwisko!=null&&wzrost!=null;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public Integer getWzrost() {
        return wzrost;
    }

    public void setWzrost(Integer wzrost) {
        this.wzrost = wzrost;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;

        //"Wyliczanie" płci:
        if (pesel.charAt(9)%2==0) {
            this.plec="K";
        } else { this.plec="M";}

        //Wyliczanie daty:
        Integer rok =Integer.parseInt(pesel.substring(0,2));
        Integer miesiac =Integer.parseInt(pesel.substring(2,4));
        if (miesiac<=20) {rok+=1900; } else {
            rok+=2000;
            miesiac-=20;
        }
        Integer dzien =Integer.parseInt(pesel.substring(4,6));
        this.data=String.valueOf(dzien)+"."+String.valueOf(miesiac)+"."+String.valueOf(rok);

        //Wylicznie wieku:
        this.wiek= Period.between(LocalDate.of(rok,miesiac,dzien),LocalDate.now()).getYears();
    }

    public String getPesel() {
        return pesel;
    }

    public String toString() {
        return imie+", "+wiek+", "+pesel;
    }
}