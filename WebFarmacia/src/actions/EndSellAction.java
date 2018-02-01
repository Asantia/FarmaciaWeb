package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.UtenteConnessoBean;
import beans.CarrelloBean;
import beans.PazienteCercatoBean;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by csantia on 1/24/2018.
 */
public class EndSellAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");
        CarrelloBean carrello = (CarrelloBean)session.getAttribute("carrello");
        PazienteCercatoBean pazCon =(PazienteCercatoBean)session.getAttribute("pazCon");

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        for(int i=0; i<carrello.getListaid().length; i++) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
                String query = "INSERT INTO vendita (idfarmaco, cf, quantita, venditore) VALUES (?,?,?,?) ";
                String query2 = "UPDATE magazzino SET disponibilita =(disponibilita-?) WHERE idfarmacia=? AND idfarmaco=?";
                st = conn.prepareStatement(query);
                st.setInt(1, carrello.getListaid()[i]);
                st.setString(2, pazCon.getCf());
                st.setInt(3, carrello.getListaq()[i]);
                st.setString(4, u.getEmail());

                st.executeUpdate();
                st.close();

                st = conn.prepareStatement(query2);
                st.setInt(1, carrello.getListaq()[i]);
                st.setInt(2, u.getIdFarmacia());
                st.setInt(3, carrello.getListaid()[i]);

                st.executeUpdate();
                st.close();

                conn.close();
            } catch (Exception e) {
                System.out.println("Impossibile la vendita nel DB: " + e.getMessage());
                if (u.getAbilitazione().equals("TF"))
                    return mapping.findForward("failTitolare");
                else
                    return mapping.findForward("failDipendenti");
            }
        }

        if(u.getAbilitazione().equals("TF"))
            return mapping.findForward("homeTitolare");
        else
            return mapping.findForward("homeDipendenti");
    }
}
