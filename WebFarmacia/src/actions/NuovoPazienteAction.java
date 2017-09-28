package actions;

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by csantia on 9/28/2017.
 */
public class NuovoPazienteAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String cf= request.getParameter("cf");
        String nome= request.getParameter("nome");
        String cognome= request.getParameter("cognome");
        String citta= request.getParameter("citta");
        String cap= request.getParameter("cap");
        String via= request.getParameter("via");
        String numero= request.getParameter("numero");
        String telefono= request.getParameter("telefono");
        Date datanascita= Date.valueOf(request.getParameter("datanascita"));

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        String insertore= u.getEmail();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
            String query="INSERT INTO paziente(cf, nome, cognome, citta, cap, via, numero, telefono, datanascita, insertore) VALUES (?,?,?,?,?,?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1, cf);
            st.setString(2, nome);
            st.setString(3, cognome);
            st.setString(4, citta);
            st.setString(5, cap);
            st.setString(6, via);
            st.setString(7, numero);
            st.setString(8, telefono);
            st.setDate(9, datanascita);
            st.setString(10, insertore);

            if(nome.trim().length()<1 || cognome.trim().length()<1 || citta.trim().length()<1 || cap.trim().length()<1 || via.trim().length()<1 || numero.trim().length()<1 || datanascita.toString().trim().length()<1) {
                if (u.getAbilitazione().equals("TF"))
                    return mapping.findForward("failTitolare");
                return mapping.findForward("failDipendenti");
            }
            if(cf.trim().length()!=16) {
                if (u.getAbilitazione().equals("TF"))
                    return mapping.findForward("failTitolare");
                return mapping.findForward("failDipendenti");
            }

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire il nuovo paziente nel DB: "+ e.getMessage() );
            if(u.getAbilitazione().equals("TF"))
                return mapping.findForward("failTitolare");
            else
                return mapping.findForward("failDipendenti");
        }

        return mapping.findForward("inserisciRicetta");
    }
}
