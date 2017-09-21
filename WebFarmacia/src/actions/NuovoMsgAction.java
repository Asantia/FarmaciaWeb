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

        if(emaildest.trim().length()<1 || oggetto.trim().length()<1 || testo.trim().length()<1)
            return mapping.findForward("failmsg");

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        String email = u.getEmail();
        Date data = new Date(Calendar.getInstance().getTime().getTime());

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        if(emaildest.equals("alltf")){
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

                String query="SELECT email from utente where abilitazione='TF'";
                st = conn.prepareStatement(query);
                rs = st.executeQuery();

                while (rs.next()){
                    emaildest = rs.getString("email");

                    try {
                        PreparedStatement st2 = null;
                        String query2="INSERT INTO messaggio(emaildest, oggetto, testo, data, email) VALUE (?,?,?,?,?)";
                        st2 = conn.prepareStatement(query2);
                        st2.setString(1,emaildest);
                        st2.setString(2,oggetto);
                        st2.setString(3,testo);
                        st2.setDate(4,data);
                        st2.setString(5,u.getEmail());
                        st2.executeUpdate();
                        st2.close();
                    }
                    catch (Exception e) {
                        System.out.println("Errore di connessione al DB: "+ e.getMessage());
                        return mapping.findForward("failmsg");
                    }
                }

                rs.close();
                st.close();
                conn.close();
            }
            catch (Exception e) {
                System.out.println("Errore di connessione al DB: "+ e.getMessage());
                return mapping.findForward("failmsg");
            }
            return mapping.findForward("messaggiRegione");
        }
        else if(emaildest.equals("alldipendenti")){
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

                String query="SELECT email from utente where idfarmacia=? AND email<>?";
                st = conn.prepareStatement(query);
                st.setInt(1, u.getIdFarmacia());
                st.setString(2, u.getEmail());
                rs = st.executeQuery();

                while (rs.next()){
                    emaildest = rs.getString("email");

                    try {
                        PreparedStatement st2 = null;
                        String query2="INSERT INTO messaggio(emaildest, oggetto, testo, data, email) VALUE (?,?,?,?,?)";
                        st2 = conn.prepareStatement(query2);
                        st2.setString(1,emaildest);
                        st2.setString(2,oggetto);
                        st2.setString(3,testo);
                        st2.setDate(4,data);
                        st2.setString(5,u.getEmail());
                        st2.executeUpdate();
                        st2.close();
                    }
                    catch (Exception e) {
                        System.out.println("Errore di connessione al DB: "+ e.getMessage());
                        return mapping.findForward("failmsg");
                    }
                }

                rs.close();
                st.close();
                conn.close();
            }
            catch (Exception e) {
                System.out.println("Errore di connessione al DB: "+ e.getMessage());
                return mapping.findForward("failmsg");
            }
            if(u.getAbilitazione().equals("TF"))
                return mapping.findForward("messaggiTitolare");
            else
                return mapping.findForward("messaggiDipendenti");
        }
        else {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

                String query = "INSERT INTO messaggio (emaildest, oggetto, testo, data, email) VALUES (?,?,?,?,?) ";
                st = conn.prepareStatement(query);
                st.setString(1, emaildest);
                st.setString(2, oggetto);
                st.setString(3, testo);
                st.setDate(4, data);
                st.setString(5, email);
                st.executeUpdate();

                st.close();
                conn.close();
            }
            catch (Exception e) {
                System.out.println("Impossibile inserire il nuovo messaggio nel DB: " + e.getMessage());
                return mapping.findForward("failmsg");
            }

            if(u.getAbilitazione().equals("RE"))
                return mapping.findForward("messaggiRegione");
            else if(u.getAbilitazione().equals("TF"))
                return mapping.findForward("messaggiTitolare");
            else
                return mapping.findForward("messaggiDipendenti");
        }
    }
}
