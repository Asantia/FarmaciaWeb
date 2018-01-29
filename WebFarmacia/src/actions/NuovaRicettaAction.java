package actions;

import beans.PazienteCercatoBean;
import beans.CarrelloBean;
import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Created by csantia on 1/29/2018.
 */
public class NuovaRicettaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String nomefarmaco = request.getParameter("nomefarmaco");
        String info = request.getParameter("info");
        Date dataricetta = Date.valueOf(request.getParameter("dataricetta"));
        int codmed = Integer.parseInt(request.getParameter("codmed"));
        int dosaggio = Integer.parseInt(request.getParameter("dosaggio"));

        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean) session.getAttribute("userCon");
        CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello") ;
        PazienteCercatoBean pazCon = (PazienteCercatoBean) session.getAttribute("pazCon");

        System.out.print("pazcon cf: "+ pazCon.getCf());

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
            String query = "INSERT INTO ricetta(farmaco, info, data, codmed, cf, dosaggio) VALUES (?,?,?,?,?,?) ";
            st = conn.prepareStatement(query);
            st.setString(1, nomefarmaco);
            st.setString(2, info);
            st.setDate(3, dataricetta);
            st.setInt(4, codmed);
            st.setString(5, pazCon.getCf());
            st.setInt(6, dosaggio);

            if (nomefarmaco.trim().length() < 1 || codmed < 1 || dosaggio < 1 ||  dataricetta.toString().trim().length() < 1) {
                if (u.getAbilitazione().equals("TF"))
                    return mapping.findForward("failTitolare");
                return mapping.findForward("failDipendenti");
            }

            st.executeUpdate();

            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Impossibile inserire il nuovo paziente nel DB: " + e.getMessage());
            if (u.getAbilitazione().equals("TF"))
                return mapping.findForward("failTitolare");
            else
                return mapping.findForward("failDipendenti");
        }

        carrello.setRicetta(carrello.getRicetta()-1);
        if(carrello.getRicetta()>0)
            return mapping.findForward("inserisciRicetta");
        else
            return mapping.findForward("venditafinita");
    }
}
