package actions;

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by csantia on 9/24/2017.
 */
public class BuyAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String nome= request.getParameter("nome");
        int dosaggio= Integer.parseInt(request.getParameter("dosaggio"));
        String ricetta= request.getParameter("ricetta");
        int quantita= Integer.parseInt(request.getParameter("quantita"));
        int idfarmaco = -1;

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        int idfarmacia = u.getIdFarmacia();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT idfarmaco FROM farmaco WHERE nome=? AND dosaggio=?" ;
            st = conn.prepareStatement(query);
            st.setString(1, nome);
            st.setInt(2, dosaggio);
            rs=st.executeQuery();

            while (rs.next()) {
                if(quantita>0)
                    idfarmaco = rs.getInt("idfarmaco");
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Errore di connessione a DB: "+ e.getMessage() );
        }

        conn = null;
        st = null;
        rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT idfarmaco FROM magazzino WHERE idfarmaco=? AND idfarmacia=?" ;
            st = conn.prepareStatement(query);
            st.setInt(1, idfarmaco);
            st.setInt(2, idfarmacia);
            rs=st.executeQuery();

            if(!rs.next()) {
                try {
                    String query2 = "INSERT INTO magazzino (idfarmaco, idfarmacia, disponibilita) VALUES (?,?,?) ";
                    st = conn.prepareStatement(query2);
                    st.setInt(1, idfarmaco);
                    st.setInt(2, idfarmacia);
                    st.setInt(3, quantita);
                    st.executeUpdate();
                }
                catch (Exception e){
                    System.out.println("Impossibile inserire il nuovo prodotto nel DB: "+ e.getMessage() );
                    return mapping.findForward("failTitolare");
                }

            }
            else{
                try {
                    String query2 = "UPDATE magazzino SET disponibilita=disponibilita+? WHERE idfarmaco=? AND idfarmacia=?";
                    st = conn.prepareStatement(query2);
                    st.setInt(1, quantita);
                    st.setInt(2, idfarmaco);
                    st.setInt(3, idfarmacia);
                    st.executeUpdate();
                }
                catch (Exception e){
                    System.out.println("Impossibile aggiornare il prodotto nel DB: "+ e.getMessage() );
                    return mapping.findForward("failTitolare");
                }
            }
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile inserire il nuovo acquisto nel DB: "+ e.getMessage() );
            return mapping.findForward("failTitolare");
        }

        return mapping.findForward("magazzino");
    }
}
