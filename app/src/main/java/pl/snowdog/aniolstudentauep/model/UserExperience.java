package pl.snowdog.aniolstudentauep.model;

/**
 * Created by chomi3 on 2015-02-26.
 */
public class UserExperience {
    private String name;
    private String date;

    public UserExperience(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
