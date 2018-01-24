package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.UtenteConnessoBean;

import javax.servlet.http.HttpSession;

/**
 * Created by csantia on 1/24/2018.
 */
public class EndSellAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        UtenteConnessoBean u = (UtenteConnessoBean)session.getAttribute("userCon");

        if(u.getAbilitazione().equals("TF"))
            response.getOutputStream().print("homeTitolare");
        else
            response.getOutputStream().print("homeDipendenti");
        return null;
    }
}
