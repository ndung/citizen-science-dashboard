/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.web.pages;

import id.go.lipi.informatika.alboom.dashboard.manager.DataManager;
import id.go.lipi.informatika.alboom.dashboard.model.Data;
import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@UrlBinding("/geoMapCluster.html")
public class GeoMapClusterActionBean extends ActionBean {

    private String uploadFrom;
    private String uploadTo;
    private String status;
    private String user;

    private DataManager dataManager;

    @SpringBean("dataManager")
    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Resolution dataMapClusterer() {
        List<Data> data = dataManager.getData(
                convertFromString(status),
                user,
                uploadFrom,
                uploadTo);
        return new StreamingResolution("text", "{\"count\":" + data.size() + ",\"content\":" + jSon(data) + "}");
    }

    private List<String> convertFromString(String str){
        if (str!=null && !str.isEmpty()){
            return Arrays.asList((str.endsWith(",") ? str.substring(0,str.length()-1) : str).split(","));
        }
        return new ArrayList<String>();
    }

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("WEB-PAGES/Dashboard/GeoMapClusterActionBean.jsp");
    }

    @Before
    public void init() {
    }

    public String getUploadFrom() {
        return uploadFrom;
    }

    public void setUploadFrom(String uploadFrom) {
        this.uploadFrom = uploadFrom;
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public void setUploadTo(String uploadTo) {
        this.uploadTo = uploadTo;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
