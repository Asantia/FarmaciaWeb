package utils;

/**
 * Created by csantia on 9/20/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmailUtil {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public EmailUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
        }
    }

    public String sent(String emailUtente){
        String output="";
        String query="SELECT emaildest , oggetto, testo, data FROM messaggio WHERE email=? ORDER BY data";
        try {
            st = conn.prepareStatement(query);
            st.setString(1, emailUtente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                output = output.concat("<tr><td><p>" + rs.getString("emaildest") + "</p></td><td><p>" + rs.getString("oggetto") + "</p></td><td><p>" + rs.getString("testo") + "</p></td><td><p>" + rs.getString("data") + "</p></td>");
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

    public String received(String emailUtente){
        String output="";
        String query="SELECT email , oggetto, testo, data FROM messaggio WHERE emaildest=?";
        try {
            st = conn.prepareStatement(query);
            st.setString(1, emailUtente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                output = output.concat("<tr><td><p>" + rs.getString("email") + "</p></td><td><p>" + rs.getString("oggetto") + "</p></td><td><p>" + rs.getString("testo") + "</p></td><td><p>" + rs.getString("data") + "</p></td>");
            }

            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );

        }
        return output;
    }

}
