/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.web.credential;

import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/welcome.html")
public class WelcomeActionBean extends ActionBean {
    @DefaultHandler
    @DontValidate
    public Resolution view() { 
      return new ForwardResolution("WEB-PAGES/Credential/WelcomeActionBean.jsp");
    }
}
