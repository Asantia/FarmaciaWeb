package Actions;

/**
 * Created by csantia on 9/8/2017.
 */

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.lang.*;
import Beans.UtenteConnessoBean;

public class LoginAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String email= request.getParameter("email");
        String password =request.getParameter("password");

        Connection conn = null;
        PreparedStatement st = null;
        String testEmail="";
        String testPassword="";
        String abilitazione="";
        int idFarmacia=-1;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

            String query="SELECT email, password, abilitazione, idfarmacia FROM utente WHERE email=? AND password=?";
            st = conn.prepareStatement(query);
            st.setString(1 ,email);
            st.setString(2 ,password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga
            {

                testEmail = rs.getString("email");

                testPassword = rs.getString("password");

                abilitazione=rs.getString("abilitazione");

                idFarmacia=rs.getInt("idfarmacia");

            }
            rs.close();
            st.close();
            conn.close();

        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );

        }

        if(!testEmail.equals(email)||(email.trim().length()<1))
            return(mapping.findForward("bad-user"));
        if(!testPassword.equals(password)||(password.trim().length()<1))
            return(mapping.findForward("bad-password"));
        else {
            UtenteConnessoBean userCon = new UtenteConnessoBean();
            userCon.setEmail(testEmail);
            userCon.setConnesso(true);
            userCon.setAbilitazione(abilitazione);
            userCon.setIdFarmacia(idFarmacia);
            request.getSession().setAttribute("utenteConnesso", userCon);
            request.getSession().setAttribute("userCon", userCon);

            return mapping.findForward("success");
        }
    }



}
