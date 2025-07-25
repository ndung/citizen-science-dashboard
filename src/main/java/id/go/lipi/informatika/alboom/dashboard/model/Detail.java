package id.go.lipi.informatika.alboom.dashboard.model;

import java.util.Date;

public class Detail {
    private Long id;
    private String question;
    private String response;
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String toString(){
        return question.concat(":").concat(response);
    }
}
