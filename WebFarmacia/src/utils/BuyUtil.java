package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by csantia on 9/22/2017.
 */
public class BuyUtil {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public BuyUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
        }
    }

    public String listout(){
        String output="";
        String query="SELECT nome , dosaggio, prezzo, ricetta FROM farmaco";
        try {
            st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                output = output.concat("<form class=\"w-100 h-100 m-3 p-3\" style=\"\" method=\"post\" action=\"buy.do\">\n" +
                        "<tr><td><p><input type=\"text\" readonly=\"readonly\" name=\"nome\" value=\"" + rs.getString("nome") +
                        "\"/></p></td><td><p><input type=\"text\" readonly=\"readonly\" name=\"dosaggio\" value=\"" + rs.getString("dosaggio") +
                        "\"/></p></td><td><p><input type=\"text\" readonly=\"readonly\" name=\"prezzo\" value=\"" + rs.getString("prezzo") +
                        "\"/></p></td><td><p><input type=\"text\" readonly=\"readonly\" name=\"ricetta\" value=\"" + rs.getString("ricetta")+
                        "\"/></p></td><td><p><input name=\"quantita\" type=\"int\" class=\"form-control\" placeholder=\"0\">"+
                        "</p></td><td><p><button type=\"submit\" class=\"btn btn-primary mx-3 w-100\">Compra</button></p></td></form>");
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
