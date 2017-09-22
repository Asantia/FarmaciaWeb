package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by csantia on 9/22/2017.
 */
public class SellUtil {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public SellUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
        }
    }

    public String listout(int idfarmaciaUtente, String abilitazioneUtente){
        String output="";
        String query;
        if(abilitazioneUtente.equals("TF") || abilitazioneUtente.equals("DF"))
            query="SELECT nome , dosaggio, prezzo, ricetta FROM farmaco JOIN magazzino ON farmaco.idfarmaco=magazzino.idfarmaco WHERE magazzino.idfarmacia=?";
        else
            query="SELECT nome , dosaggio, prezzo, ricetta FROM farmaco JOIN magazzino ON farmaco.idfarmaco=magazzino.idfarmaco WHERE abilitazione='OB' AND magazzino.idfarmacia=?";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1,idfarmaciaUtente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                output = output.concat("<tr><td><p>" + rs.getString("nome") + "</p></td><td><p>" + rs.getString("dosaggio") + "</p></td><td><p>" + rs.getString("prezzo") + "</p></td><td><p>" + rs.getString("ricetta") + "</p></td>");
            }

            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB"+ e.getMessage() );
        }
        return output;
    }

}
