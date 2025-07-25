/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.web;

import id.go.lipi.informatika.alboom.dashboard.model.Menu;
import id.go.lipi.informatika.alboom.dashboard.model.User;
import id.go.lipi.informatika.alboom.dashboard.function.ConstantaUtil;
import java.io.IOException;
import java.util.List;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.StripesFilter;
import org.codehaus.jackson.map.ObjectMapper;

public class ActionBean implements net.sourceforge.stripes.action.ActionBean {

    private BaseActionBeanContext ctx;

    private final String SESSION_USER = "SESSION_USER";
    private final String SESSION_LINK = "SESSION_LINK";
    private final String SESSION_NOTIF_SOUND = "SESSION_NOTIF_SOUND";
    protected int pageSize = 20;

    @Override
    public BaseActionBeanContext getContext() {
        return this.ctx;
    }

    @Override
    public void setContext(ActionBeanContext ctx) {
        this.ctx = (BaseActionBeanContext) ctx;
    }

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    protected String getView(String group, Class c) {
        return "/WEB-PAGES/" + group + "/" + c.getSimpleName() + ".jsp";
    }

    protected void setUserSession(User user) {
        getContext().setSession(SESSION_USER, user, 600);
    }

    public User getUserSession() {
        User user = (User) getContext().getSession(SESSION_USER);
        return user;
    }

    protected void setNotifSoundSession(Boolean notifSound) {
        getContext().setSession(SESSION_NOTIF_SOUND, notifSound);
    }

    public Boolean getNotifSoundSession() {
        Boolean notifSound = (Boolean) getContext().getSession(SESSION_NOTIF_SOUND);
        return notifSound;
    }

    public String getCurrentUrl() {
        String uri = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(getClass());
        if (getContext().getRequest().getQueryString() != null) {
            uri += "?" + getContext().getRequest().getQueryString().replace("&page=" + pageSize, "").replace("page=" + pageSize, "");
        }
        return uri;
    }

    protected void setMenuSession(List<Menu> menus) {
        getContext().setSession(SESSION_LINK, menus);
    }

    public List<Menu> getUserMenus() {
        List<Menu> menus = (List<Menu>) getContext().getSession(SESSION_LINK);
        return menus;
    }
    
    public String getLink() {
        String menu = "";
        List<Menu> menus = getUserMenus();
        for (Menu menuz : menus) {
            if (menuz.getStatus()==1) {
                if (menuz.getLink() != null) {
                    menu += "<li><a href=\"" + ConstantaUtil.rootPath + menuz.getLink() + "\">" +
                            "<i class=\"" + menuz.getIcon() + "\"></i> " +
                            "<span class=\"nav-label\">" + menuz.getName() + "</span></a>" +
                            "</li>";
                }
            }
        }
        return menu;
    }

    public void setPageSize(int page) {
        this.pageSize = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    protected String jSon(List lists) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object[] array = lists.toArray(new Object[lists.size()]);
            return mapper.writeValueAsString(array);
        } catch (IOException e) {
            return null;
        }
    }

    protected String jSon(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

}
