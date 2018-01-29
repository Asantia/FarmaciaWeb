package actions;

import beans.UtenteConnessoBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Arrays;

import beans.CarrelloBean;
/**
 * Created by csantia on 9/25/2017.
 */
public class SellAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {

        int[] a = Arrays.stream(request.getParameterValues("prodotti")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(request.getParameterValues("quantita")).mapToInt(Integer::parseInt).toArray();

        CarrelloBean carrello = new CarrelloBean();
        carrello.setListaid(a);
        carrello.setListaq(b);

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;

        for(int i=0; i<a.length; i++){
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

                String query="SELECT prezzo, ricetta FROM farmaco WHERE idfarmaco=?" ;
                st = conn.prepareStatement(query);
                st.setInt(1, a[i]);
                rs=st.executeQuery();

                while (rs.next()) {
                    carrello.setPrezzo(carrello.getPrezzo()+rs.getInt("prezzo")*b[i]);
                    if(rs.getBoolean("ricetta"))
                        carrello.setRicetta(carrello.getRicetta()+1);
                }
                rs.close();
                st.close();
                conn.close();
            }
            catch (Exception e) {
                System.out.println("Errore di connessione a DB: "+ e.getMessage() );
                return mapping.findForward("failTitolare");
            }
        }
        request.getSession().setAttribute("carrello", carrello);
        System.out.print("Numero ricette: "+carrello.getRicetta());
        if(carrello.getRicetta()>0)
            response.getOutputStream().print("cercaPazienteTitolare");
        else
            response.getOutputStream().print("venditafinita");
        return null;
        //return mapping.findForward("cercaPazienteTitolare");
        /*
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

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");

            String query="SELECT idfarmaco FROM farmaco WHERE nome=? AND dosaggio=?" ;
            st = conn.prepareStatement(query);
            st.setString(1, nome);
            st.setInt(2, dosaggio);
            rs=st.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("idfarmaco"));
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Errore di connessione a DB: "+ e.getMessage() );
            return mapping.findForward("failTitolare");
        }

        return mapping.findForward("cercaPazienteTitolare");
        */
    }
}