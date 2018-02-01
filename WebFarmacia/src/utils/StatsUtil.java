package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by csantia on 9/22/2017.
 */
public class StatsUtil {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public StatsUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
        }
    }

    public String bestselling(){
        String output="";
        String query="SELECT COUNT(farmaco.nome) AS cont,farmaco.nome,farmaco.dosaggio FROM farmaco JOIN vendita ON farmaco.idfarmaco = vendita.idfarmaco GROUP BY farmaco.nome, farmaco.dosaggio ORDER BY cont DESC";
        try {
            st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            int i=1;
            while (rs.next() && i<=10) {
                output = output.concat("<tr><td><p>" + i + ". </p></td><td><p>" + rs.getString("nome") + "</p></td><td><p>" + rs.getString("dosaggio") + "</p></td><td><p>" + rs.getString("cont") + "</p></td>");
                i++;
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

    public String bestselling(int idfarmaciaUtente){

        String output="";
        String query="SELECT COUNT(farmaco.nome) AS cont,\n" +
                "    farmaco.nome,\n" +
                "    farmaco.dosaggio\n" +
                "  FROM farmaco\n" +
                "    JOIN vendita ON farmaco.idfarmaco = vendita.idfarmaco\n" +
                "    JOIN utente ON venditore = email\n" +
                "  WHERE utente.idfarmacia = ?\n" +
                "    GROUP BY farmaco.nome, farmaco.dosaggio\n" +
                "  ORDER BY cont DESC";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1,idfarmaciaUtente);
            ResultSet rs = st.executeQuery();

            int i=1;
            while (rs.next() && i<=10) {
                output = output.concat("<tr><td><p>" + i + ". </p></td><td><p>" + rs.getString("nome") + "</p></td><td><p>" + rs.getString("dosaggio") + "</p></td><td><p>" + rs.getString("cont") + "</p></td>");
                i++;
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
