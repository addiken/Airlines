package sample;

public class Mimimal {
    private String type;
    private int at;

    public Mimimal(String type, int at){
        this.at = at; this.type = type;
    };
    public int getAt() {
        return at;
    }
    public String getType() {
        return type;
    }
    public void setAt(int at) {
        this.at = at;
    }
    public void setType(String type) {
        this.type = type;
    }
}
