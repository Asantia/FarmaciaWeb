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

public class NuovaFarmaciaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String nomeFarmacia= request.getParameter("nomeFarmacia");
        String citta= request.getParameter("citta");
        String via= request.getParameter("via");
        String numero= request.getParameter("numero");
        String telefono= request.getParameter("telefono");
        String cap= request.getParameter("cap");
        int idfarmacia=0; //lo useremo dopo

        String email= request.getParameter("email");
        String password= request.getParameter("password");
        String nomeTitolare= request.getParameter("nomeTitolare");
        String cognome= request.getParameter("cognome");

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;



        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="INSERT INTO farmacia (nome, citta, via, numero, telefono, cap) VALUES (?,?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1, nomeFarmacia);
            st.setString(2, citta);
            st.setString(3, via);
            st.setString(4, numero);
            st.setString(5, telefono);
            st.setString(6, cap);

            if(nomeFarmacia.trim().length()<1 || citta.trim().length()<1 || via.trim().length()<1 || numero.trim().length()<1 || cap.trim().length()<1)
                return mapping.findForward("failRegione");

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire la nuova farmacia nel DB: "+ e.getMessage() );
            return mapping.findForward("failRegione");
        }

        conn = null;
        rs=null;
        st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT idfarmacia FROM farmacia WHERE nome=?" ;
            st = conn.prepareStatement(query);
            st.setString(1, nomeFarmacia);
            rs = st.executeQuery();

            while (rs.next()) {
                idfarmacia = rs.getInt("idfarmacia");
                System.out.println("Id nuova farmacia e': " + idfarmacia);
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile trovare idfarmacia nel DB: "+ e.getMessage() );
            return mapping.findForward("failRegione");
        }

        conn = null;
        rs=null;
        st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="INSERT INTO utente (email, password, nome, cognome, abilitazione, idfarmacia)VALUES (?,?,?,?,?,?)" ;
            st = conn.prepareStatement(query);
            st.setString(1,email);
            st.setString(2,password);
            st.setString(3,nomeTitolare);
            st.setString(4,cognome);
            st.setString(5,"TF");
            st.setInt(6, idfarmacia);

            if(email.trim().length()<1 || password.trim().length()<1 || nomeTitolare.trim().length()<1 || cognome.trim().length()<1)
                return mapping.findForward("failRegione");

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire il titolare nel DB: "+ e.getMessage() );
            return mapping.findForward("failRegione");
        }

        return mapping.findForward("successRegione");
    }
}
