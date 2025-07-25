package id.go.lipi.informatika.alboom.dashboard.model;

public class Role implements java.io.Serializable {

    private Integer id;
    private String name;
    private String level;
    
    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
