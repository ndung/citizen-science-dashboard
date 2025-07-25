package id.go.lipi.informatika.alboom.dashboard.web.credential;


import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/AccessDenied.html")
public class AccessDeniedActionBean extends ActionBean {
    
    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("/WEB-PAGES/Credential/AccessDeniedActionBean.jsp");
    }
	
}
