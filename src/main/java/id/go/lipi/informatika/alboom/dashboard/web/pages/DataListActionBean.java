package id.go.lipi.informatika.alboom.dashboard.web.pages;

import id.go.lipi.informatika.alboom.dashboard.manager.DataManager;
import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import id.go.lipi.informatika.alboom.dashboard.model.Data;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.ArrayList;
import java.util.List;

@UrlBinding("/list.html")
public class DataListActionBean extends ActionBean {

    private String status;
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private DataManager dataManager;

    @SpringBean("dataManager")
    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Resolution data() {
        List<Data> data = dataManager.getData(null, null, null, null);
        List<Data> newData = new ArrayList<Data>();
        for (int i=0;i<data.size();i++){
            Data a = data.get(i);
            a.setNo(data.size()-i);
            newData.add(a);
        }
        return new StreamingResolution("text", jSon(newData));
    }

    public Resolution verify(){
        dataManager.updateData(status, id, getUserSession().getUsername());
        return new StreamingResolution("text", "{\"result\":true}");
    }

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("WEB-PAGES/Dashboard/DataListActionBean.jsp");
    }

    @Before
    public void init() {
    }

}
