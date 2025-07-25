package id.go.lipi.informatika.alboom.dashboard.model;

import java.io.Serializable;
import java.util.Date;

public class Image implements Serializable {

    private String id;
    private String sectionId;
    private String url;
    private Date createAt;
    private int status;
    private String verificator;
    private Date verificationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionId() { return sectionId; }

    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVerificator() {
        return verificator;
    }

    public void setVerificator(String verificator) {
        this.verificator = verificator;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }
}
