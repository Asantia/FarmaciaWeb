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
import beans.CarrelloBean;

/**
 * Created by csantia on 9/25/2017.
 */
public class FillCarrelloAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nome");
        int dosaggio = Integer.parseInt(request.getParameter("dosaggio"));
        boolean ricetta = Boolean.parseBoolean(request.getParameter("ricetta"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        int idfarmaco = -1;

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean) session.getAttribute("userCon");
        int idfarmacia = u.getIdFarmacia();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        if(quantita>0) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

                String query = "SELECT idfarmaco, ricetta FROM farmaco WHERE nome=? AND dosaggio=?";
                st = conn.prepareStatement(query);
                st.setString(1, nome);
                st.setInt(2, dosaggio);
                rs = st.executeQuery();

                while (rs.next()) {
                    System.out.print("id farmaco: "+idfarmaco+" ");
                }
                rs.close();
                st.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Errore di connessione a DB: " + e.getMessage());
                return mapping.findForward("failTitolare");
            }
        }
        return mapping.findForward("vendixTitolare");
    }
}
