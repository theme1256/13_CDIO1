package dto;

public class UserSubmit {
    private String username;
    private String ini;
    private int userID;
    private String cpr;
    private boolean pharmaceut;

    public UserSubmit(){}

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPharmaceut(boolean pharmaceut) {
        this.pharmaceut = pharmaceut;
    }

    public String getCpr() {
        return cpr;
    }

    public int getUserID() {
        return userID;
    }

    public String getIni() {
        return ini;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPharmaceut() {
        return pharmaceut;
    }
}
