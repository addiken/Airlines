package sample;

public class Flight {
    private int id;
    private String type;
    private String city;
    private String tt;
    private int at;

    Flight(int id, String t, String c, String tt, int at){
        this.id=id;this.type=t;this.city=c;this.tt=tt;this.at=at;
    };
    //геттеры
    public int getAt() {
        return at;
    }
    public int getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public String getTt() {
        return tt;
    }
    public String getType() {
        return type;
    }
    //сеттеры
    public void setAt(int at) {
        this.at = at;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTt(String tt) {
        this.tt = tt;
    }
    public void setType(String type) {
        this.type = type;
    }
}
