package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.PazienteCercatoBean;
import java.sql.*;

/**
 * Created by csantia on 9/23/2017.
 */
public class CercaPazienteAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String cf = request.getParameter("cf");
        String nome =request.getParameter("nome");
        String cognome =request.getParameter("cognome");
        Date datanascita = Date.valueOf(request.getParameter("datanascita"));

        Connection conn = null;
        PreparedStatement st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT cf, nome, cognome, datanascita FROM paziente WHERE cf=? AND nome=? AND cognome=? AND datanascita=?";
            st = conn.prepareStatement(query);
            st.setString(1 ,cf);
            st.setString(2 ,nome);
            st.setString(3 ,cognome);
            st.setDate(4 ,datanascita);
            ResultSet rs = st.executeQuery();

            if(rs!=null) {
                rs.close();
                st.close();
                conn.close();

                PazienteCercatoBean pazConn = new PazienteCercatoBean();
                pazConn.setCf(cf);
                pazConn.setNome(nome);
                pazConn.setCognome(cognome);
                pazConn.setDatanascita(datanascita);
                return mapping.findForward("pazientiTrovati");
            }

        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );

        }

        return mapping.findForward("inserisciPaziente");
    }
}
