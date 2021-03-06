package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

    private static final long serialVersionUID = 4545864587995944260L;
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;
    private String cpr;
    private String password;
    private List<String> possible_roles = new ArrayList<String>();

    public UserDTO() {
        this.roles = new ArrayList<String>();
        this.possible_roles.add("Admin");
        this.possible_roles.add("Pharmacist");
        this.possible_roles.add("Laborant");
        this.possible_roles.add("Produktionsleder");
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) throws DTOException {
        if (userId >= 11 && userId <= 99)
            this.userId = userId;
        else
            throw new DTOException("ID er ikke i den tilladte range");
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) throws DTOException {
        if (userName.length() >= 2  && userName.length() <= 20)
            this.userName = userName;
        else
            throw new DTOException("Username er ikke i den talladte længde");
    }
    public String getIni() {
        return ini;
    }
    public void setIni(String ini) throws DTOException {
        if (ini.length() >= 2  && ini.length() <= 4)
            this.ini = ini;
        else
            throw new DTOException("Initialer er ikke i den talladte længde");
    }
    public String getCpr() {
        return this.cpr;
    }
    public void setCpr(String cpr) throws DTOException {
        if (cpr.length() == 11)
            this.cpr = cpr;
        else
            throw new DTOException("CPR er ikke i den talladte længde");
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) throws DTOException {
        for (Object test : roles.toArray()) {
            if (!this.possible_roles.contains(test)) {
                throw new DTOException("Rolle " + test.toString() + " er ikke en godkendt rolle");
            }
        }
        this.roles = roles;
    }

    public void addRole(String role) throws DTOException {
        if (!this.possible_roles.contains(role)) {
            throw new DTOException("Rolle " + role + " er ikke en godkendt rolle");
        }
        this.roles.add(role);
    }


    public void setPassword(String password) throws DTOException {
        if (password.length() >= 6  && password.length() <= 50)
            this.password = password;
        else
            throw new DTOException("Password er ikke i den talladte længde");

    }
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @param role
     * @return true if role existed, false if not
     */
    public boolean removeRole(String role){
        return this.roles.remove(role);
    }
    public void removeRoles() {
        this.roles = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "UserDTO [userId: " + this.userId + " | userName: " + this.userName + " | ini: " + this.ini + " | roles: " + this.roles + " | CPR: " + this.cpr + " | password: " + this.password + "]";
    }

    public class DTOException extends Exception {
        /**
         *
         */
        private static final long serialVersionUID = 7355418246336739229L;

        public DTOException(String msg, Throwable e) {
            super(msg,e);
        }

        public DTOException(String msg) {
            super(msg);
        }
    }

}