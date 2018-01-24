package beans;

import java.util.ArrayList;

/**
 * Created by csantia on 9/25/2017.
 */
public class CarrelloBean implements java.io.Serializable {
    int[] listaid=null;
    int[] listaq=null;
    double prezzo=0;
    boolean ricetta=false;

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

    public boolean isRicetta() {
        return ricetta;
    }

    public void setRicetta(boolean ricetta) {
        this.ricetta = ricetta;
    }
}
