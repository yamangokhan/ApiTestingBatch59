package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
    1) Tüm keyler için Private veriable'lar  olusturulur
    2) Tüm parametrelerle ve parametresiz constructor larımızı olsuturuyoruz (1 parametreli 1 parametresiz )
    4) Getters ve Setters larımızı olusturuyoruz.
    5) toString() methodumuzu olusturuyoruz.
     */

    private int userID;
    private String title;
    private Boolean completed;

    public JsonPlaceHolderPojo(int userID, String title, Boolean completed) {
        this.userID = userID;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userID=" + userID +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    // Farklı key value ikililerin uyusmazlıgını @JsonIgnoreProperties(ignore=true) ile
}
