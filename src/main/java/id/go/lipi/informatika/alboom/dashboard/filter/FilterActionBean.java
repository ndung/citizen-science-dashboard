package id.go.lipi.informatika.alboom.dashboard.filter;

import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import id.go.lipi.informatika.alboom.dashboard.web.credential.WelcomeActionBean;
import id.go.lipi.informatika.alboom.dashboard.model.Menu;
import id.go.lipi.informatika.alboom.dashboard.model.User;
import id.go.lipi.informatika.alboom.dashboard.web.credential.AccessDeniedActionBean;
import id.go.lipi.informatika.alboom.dashboard.web.credential.LoginActionBean;

import java.util.List;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.StripesFilter;

@Intercepts({LifecycleStage.HandlerResolution})
public class FilterActionBean implements Interceptor {

    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    private HttpSession session;

    @Override
    public Resolution intercept(ExecutionContext ctx) throws Exception {
        int isAccess = 0;
        User user = null;
        ActionBean actionBean = (ActionBean) ctx.getActionBean();
        String url = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(actionBean.getClass());
        String ad = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(AccessDeniedActionBean.class);
        String loginpage = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(LoginActionBean.class);
        String welcomepage = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(WelcomeActionBean.class);

        if (actionBean.getClass().isAnnotationPresent(DoesNotRequireLogin.class)) {
            isAccess = 1;
        }

        if (isAccess == 0 && !ad.equals(url)) {
            session = ctx.getActionBeanContext().getRequest().getSession();
            user = (User) session.getAttribute("SESSION_USER");
            List<Menu> menus = (List<Menu>) session.getAttribute("SESSION_LINK");
            if (user != null) {
                if (welcomepage.endsWith(url)) {
                    isAccess = 1;
                } else {
                    for (Menu menu : menus) {
                        if(menu.getLink()!=null&&url.contains(menu.getLink())){
                            isAccess=1;
                            break;
                        }
                    }
                    if(isAccess==0) isAccess = 2;
                }
            }
        } else if (isAccess == 0 && ad.equals(url)) { //if AccessDenied page
            isAccess = 1;
        }

        if (user != null && loginpage.endsWith(url)) { //if Login page but user is exist, goto welcome page
            isAccess = 3;
        }
        System.out.println("user:"+user);
        System.out.println("isAccess:"+isAccess);
        if(user==null||isAccess!=1){
            logger.info((user!=null ? user.getUsername():"unknown_user") + ":" + url + ":" + isAccess);
        }
        switch (isAccess) {
            case 1:
                return ctx.proceed();
            case 2:
                return new RedirectResolution(AccessDeniedActionBean.class);
            case 3:
                return new RedirectResolution(WelcomeActionBean.class);
            default:
                if(user!=null){
                    return new RedirectResolution(AccessDeniedActionBean.class);
                } else {
                    return new RedirectResolution(LoginActionBean.class);
                }
        }
    }
}
