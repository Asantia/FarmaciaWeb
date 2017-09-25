package beans;

import java.util.ArrayList;

/**
 * Created by csantia on 9/25/2017.
 */
public class CarrelloBean {
    ArrayList<Integer> listaid=null;
    double prezzo=0;
    boolean ricetta=false;

    public ArrayList<Integer> getListaid() {
        return listaid;
    }

    public void setListaid(ArrayList<Integer> listaid) {
        this.listaid = listaid;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isRicetta() {
        return ricetta;
    }

    public void setRicetta(boolean ricetta) {
        this.ricetta = ricetta;
    }
}
