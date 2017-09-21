package actions;

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import sun.util.resources.cldr.CalendarData;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by csantia on 9/21/2017.
 */
public class NuovoMsgAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String emaildest= request.getParameter("emaildest");
        String oggetto= request.getParameter("oggetto");
        String testo= request.getParameter("testo");

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        String email = u.getEmail();
        Date data = new Date(Calendar.getInstance().getTime().getTime());

        Connection conn = null;
        PreparedStatement st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="INSERT INTO messaggio (emaildest, oggetto, testo, data, email) VALUES (?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1, emaildest);
            st.setString(2, oggetto);
            st.setString(3, testo);
            st.setDate(4, data);
            st.setString(5, email);
            System.out.println("emaildest: "+emaildest+" oggetto: "+oggetto+" testo: "+testo+" data: "+String.valueOf(new CalendarData())+" email: "+email);

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire il nuovo messaggio nel DB: "+ e.getMessage() );
        }

        if(u.getAbilitazione().equals("RE"))
            return mapping.findForward("messaggiRegione");
        if(u.getAbilitazione().equals("TF"))
            return mapping.findForward("messaggiTitolare");
        else
            return mapping.findForward("messaggiDipendenti");
    }
}
