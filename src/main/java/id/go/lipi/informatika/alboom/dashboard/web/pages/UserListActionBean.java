package id.go.lipi.informatika.alboom.dashboard.web.pages;

import id.go.lipi.informatika.alboom.dashboard.manager.UserManager;
import id.go.lipi.informatika.alboom.dashboard.model.User;
import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

@UrlBinding("/users.html")
public class UserListActionBean extends ActionBean {

    private UserManager userManager;

    @SpringBean("userManager")
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public Resolution dataUsers() {
        List<User> data = userManager.getUsers();
        return new StreamingResolution("text", jSon(data));
    }

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("WEB-PAGES/Dashboard/UserListActionBean.jsp");
    }

    @Before
    public void init() {
    }

}
