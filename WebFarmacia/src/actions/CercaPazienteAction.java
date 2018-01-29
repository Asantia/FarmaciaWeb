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

        if(cf.trim().length()!=16)
            return mapping.findForward("failTitolare");
        if(nome.trim().length()<1 || cognome.trim().length()<1 || datanascita.toString().length()<1)
            return mapping.findForward("failTitolare");

        PazienteCercatoBean pazCon = new PazienteCercatoBean();
        pazCon.setCf(cf);
        pazCon.setNome(nome);
        pazCon.setCognome(cognome);
        pazCon.setDatanascita(datanascita);

        request.getSession().setAttribute("pazCon", pazCon);
        System.out.print("pazcon cf: "+ pazCon.getCf());

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

            while(rs.next()) {
                rs.close();
                st.close();
                conn.close();

                return mapping.findForward("pazientiTrovatiTitolare");
            }

        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );
            return mapping.findForward("failTitolare");
        }

        return mapping.findForward("inserisciPaziente");
    }
}
