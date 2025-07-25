package id.go.lipi.informatika.alboom.dashboard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Data implements Serializable {

    private int no;
    private String id;
    private double latitude;
    private double longitude;
    private double accuracy;
    private Date startDate;
    private Date finishDate;
    private String notes;
    private int status;
    private Date createAt;
    private User user;
    private String verificator;
    private Date verificationTime;
    private String imagePath;
    private List<Image> images;
    private List<Detail> details;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAccuracy() { return accuracy; }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateAt() { return createAt; }

    public void setCreateAt(Date createAt) { this.createAt = createAt; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Image> getImages() { return images; }

    public void setImages(List<Image> images) { this.images = images; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public List<Detail> getDetails() { return details; }

    public void setDetails(List<Detail> details) { this.details = details; }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String metadata;

    public String getMetadata() {
        StringBuilder sb = new StringBuilder();
        if (details!=null){
            for (Detail d: details){
                sb.append(d).append("; ");
            }
        }
        this.metadata = sb.toString();
        return metadata;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", status=" + status +
                '}';
    }
}
