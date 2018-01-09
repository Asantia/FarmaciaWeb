package actions;

/**
 * Created by csantia on 9/13/2017.
 */
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.lang.*;
import beans.UtenteConnessoBean;

public class LoginAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String emailForm = request.getParameter("email");
        String passwordForm =request.getParameter("password");

        Connection conn = null;
        PreparedStatement st = null;
        String emailDB="";
        String passwordDB="";
        String abilitazione="";
        int idFarmacia=-1;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT email, password, abilitazione, idfarmacia FROM utente WHERE email=? AND password=?";
            st = conn.prepareStatement(query);
            st.setString(1 ,emailForm);
            st.setString(2 ,passwordForm);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                emailDB = rs.getString("email");
                passwordDB = rs.getString("password");
                abilitazione = rs.getString("abilitazione");
                idFarmacia = rs.getInt("idfarmacia");

            }
            rs.close();
            st.close();
            conn.close();

        }
        catch (Exception e) {
            System.out.println("Errore di connessione al DB "+ e.getMessage() );

        }

        if(!emailDB.equals(emailForm)||(emailForm.trim().length()<1)){
            return(mapping.findForward("loginerrato"));
        }
        if(!passwordDB.equals(passwordForm)||(passwordForm.trim().length()<1)){
            return(mapping.findForward("loginerrato"));
        }
        else {
            UtenteConnessoBean userCon = new UtenteConnessoBean();
            userCon.setEmail(emailDB);
            userCon.setConnesso(true);
            userCon.setAbilitazione(abilitazione);
            userCon.setIdFarmacia(idFarmacia);
            request.getSession().setAttribute("utenteConnesso", userCon);
            request.getSession().setAttribute("userCon", userCon);
            System.out.print("email bean: "+userCon.getEmail()+" abilitazione bean: "+userCon.getAbilitazione()+" idfarmacia: "+userCon.getIdFarmacia()+" connesso? "+userCon.getConnesso());

            if(abilitazione.equals("RE"))
                return mapping.findForward("homeRegione");
            if(abilitazione.equals("TF"))
                return mapping.findForward("homeTitolare");
            else
                return mapping.findForward("homeDipendenti");
        }
    }
}
