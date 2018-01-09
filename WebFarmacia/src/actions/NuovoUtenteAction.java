package actions;

/**
 * Created by csantia on 9/13/2017.
 */

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.lang.*;

public class NuovoUtenteAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String email= request.getParameter("email");
        String password= request.getParameter("password");
        String nome= request.getParameter("nome");
        String cognome= request.getParameter("cognome");
        String abilitazione= request.getParameter("abilitazione");

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        int idfarmacia = u.getIdFarmacia();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="INSERT INTO utente (email, password, nome, cognome, abilitazione, idfarmacia) VALUES (?,?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, nome);
            st.setString(4, cognome);
            st.setString(5, abilitazione);
            st.setInt(6, idfarmacia);
            if(email.trim().length()<1 || password.trim().length()<1 || nome.trim().length()<1 || cognome.trim().length()<1)
                return mapping.findForward("failTitolare");
            if(!((abilitazione.equals("DF")) || (abilitazione.equals("OB"))))
                return mapping.findForward("failTitolare");

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire il nuovo utente nel DB: "+ e.getMessage() );
            return mapping.findForward("failTitolare");
        }

        return mapping.findForward("successTitolare");
    }
}
