package utils;
import beans.Farmaco;
import beans.ListaProdotti;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Created by csantia on 1/9/2018.
 */
public class BuyMedicinalOb {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public BuyMedicinalOb() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        } catch (Exception e) {
            System.out.println("Impossibile connettersi al database" + e.getMessage());

        }
    }

    public String VendiMedicinali(int farmacia,String permessi) {
        String query;

        if (!permessi.equals("OB")) {
            query = "SELECT farmaco.idfarmaco, farmaco.nome, farmaco.abilitazione, farmaco.prezzo, magazzino.disponibilita FROM farmaco JOIN magazzino ON farmaco.idfarmaco = magazzino.idfarmaco WHERE idfarmacia=? AND magazzino.disponibilita>0";
        } else
            query = "SELECT farmaco.idfarmaco, farmaco.nome, farmaco.abilitazione, farmaco.prezzo, magazzino.disponibilita FROM farmaco JOIN magazzino ON farmaco.idfarmaco = magazzino.idfarmaco WHERE idfarmacia=? AND farmaco.abilitazione='OB' AND magazzino.disponibilita>0";
        String out = "";

        try {
            st = conn.prepareStatement(query);
            st.setInt(1, farmacia);
            rs = st.executeQuery();
            int x = 0;
            while (rs.next()) {
                out = out.concat("<tr><td><p>" + rs.getInt(1) + "</p></td><td><p>" + rs.getString(2) + "</p></td>");
                if (!permessi.equals("OB")) {
                    String s="";
                    if(rs.getString(3).equals("DF")){
                        s="1";
                    }
                    else{
                        s="0";
                    }
                    out = out.concat("<td><p>" +s+ "</p></td>");

                }
                out = out.concat("<td><p>" + rs.getBigDecimal(4) + " &#8364</p></td><td>" + rs.getInt(5) + "</p></td><td><p><input type=\"text\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" value=\"0\" class=\"ordina\"><input class=\"add\"type=\"button\" id=\"add" + x + "\" value=\"+\"><input class=\"sub\"type=\"button\" id=\"sub" + x++ + "\" value=\"-\">");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }



    public String listaRicetta(ListaProdotti acquisto) {
        String query;
        String out = "";
        try {
            for (Farmaco prodottoAcquistato : acquisto) {
                query = "SELECT nome FROM farmaco WHERE idfarmaco=? AND abilitazione='DF'";
                st = conn.prepareStatement(query);
                st.setInt(1, prodottoAcquistato.getProdotto());
                rs = st.executeQuery();
                int x = 0;
                if (rs.next()) {
                    out = out.concat("<tr><td><p>" + prodottoAcquistato.getProdotto() + "</p></td><td><p>" + rs.getString(1) + "</p></td><td><input type=\"text\" name=\"cr" + x + "\" id=\"cr" + x++ + "\" class=\"cr\" ></td></tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }


    public String listaAcquisto(ListaProdotti acquisto) {
        String query;
        String out = "";
        try {
            for (Farmaco prodottoAcquistato : acquisto) {
                query = "SELECT nome_farmaco,costo FROM farmaco WHERE id_farmaco=?";
                st = conn.prepareStatement(query);
                st.setInt(1, prodottoAcquistato.getProdotto());
                rs = st.executeQuery();
                if (rs.next()) {
                    out = out.concat("<tr><td><p>" + prodottoAcquistato.getProdotto() + "</p></td><td><p>" + rs.getString(1) + "</p></td><td><p>" + rs.getString(2) + "</p></td><td><p>" + prodottoAcquistato.getQuantita() + "</p></td></tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String prezzo(ListaProdotti acquisto) {
        String query;
        double out = 0;
        try {
            for (Farmaco prodottoAcquistato : acquisto) {
                query = "SELECT prezzo FROM farmaco WHERE idfarmaco=?";
                st = conn.prepareStatement(query);
                st.setInt(1, prodottoAcquistato.getProdotto());
                rs = st.executeQuery();
                if (rs.next()) {
                    out += rs.getBigDecimal(1).doubleValue() * prodottoAcquistato.getQuantita();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(out) + " €";
    }

    public void close() {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
