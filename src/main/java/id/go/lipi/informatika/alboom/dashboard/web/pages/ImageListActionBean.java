package id.go.lipi.informatika.alboom.dashboard.web.pages;

import id.go.lipi.informatika.alboom.dashboard.manager.DataManager;
import id.go.lipi.informatika.alboom.dashboard.model.Data;
import id.go.lipi.informatika.alboom.dashboard.model.Image;
import id.go.lipi.informatika.alboom.dashboard.web.ActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

@UrlBinding("/images.html")
public class ImageListActionBean extends ActionBean {

    private String status;
    private String id;
    private String image;
    private Data data;
    private String notes;
    private String message;

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

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Data getData() { return data; }

    public void setData(Data data) { this.data = data; }

    private DataManager dataManager;

    @SpringBean("dataManager")
    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Resolution data() {
        data = dataManager.getDatum(id);
        List<Image> data = dataManager.getImages(id);
        return new StreamingResolution("text", jSon(data));
    }

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("WEB-PAGES/Dashboard/ImageListActionBean.jsp");
    }

    @Before
    public void init() {
        if (data ==null && id!=null) {
            data = dataManager.getDatum(id);
        }
        if (data !=null){
            id = data.getId();
        }
    }

    public Resolution verify(){
        dataManager.updateImage(status, image, getUserSession().getUsername());
        return new StreamingResolution("text", "{\"result\":true}");
    }

    public Resolution update(){
        dataManager.updateNotes(message, id, getUserSession().getUsername());
        return new StreamingResolution("text", "{\"result\":true}");
    }

    @DontValidate
    public Resolution back() {
        return new RedirectResolution(DataListActionBean.class);
    }
}
