package utils;

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.sql.*;

/**
 * Created by csantia on 9/22/2017.
 */
public class CercaPazienteUtil extends Action{
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public CercaPazienteUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
        }
    }

    public String cerca(String cfForm, String nomeForm, String cognomeForm, Date datanascitaForm){
        String cf = cfForm;
        String nome = nomeForm;
        String cognome = cognomeForm;
        Date datanascita = datanascitaForm;
        String output="";
        String query="SELECT cf , nome, cognome, datanascita FROM paziente WHERE cf=? AND nome=? AND cognome=? AND datanascita=?";
        try {
            st = conn.prepareStatement(query);
            st.setString(1, cf);
            st.setString(2, nome);
            st.setString(3, cognome);
            st.setDate(4, datanascita);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                output = output.concat("<tr><td><p>" + rs.getString("cf") + "</p></td><td><p>" + rs.getString("nome") + "</p></td><td><p>" + rs.getString("cognome") + "</p></td><td><p>" + rs.getString("datanascita") + "</p></td>");
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
