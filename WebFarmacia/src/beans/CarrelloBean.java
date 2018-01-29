package beans;

import java.util.ArrayList;

/**
 * Created by csantia on 9/25/2017.
 */
public class CarrelloBean implements java.io.Serializable {
    int[] listaid=null;
    int[] listaq=null;
    double prezzo=0;
    int ricetta=0;

    public CarrelloBean(){}

    public int[] getListaid() {
        return listaid;
    }

    public void setListaid(int[] listaid) {
        this.listaid = listaid;
    }

    public int[] getListaq() {
        return listaq;
    }

    public void setListaq(int[] listaq) {
        this.listaq = listaq;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getRicetta() {
        return ricetta;
    }

    public void setRicetta(int ricetta) {
        this.ricetta = ricetta;
    }
}
