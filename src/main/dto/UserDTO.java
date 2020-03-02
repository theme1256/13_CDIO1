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
        this.possible_roles.add("Foreman");
        this.possible_roles.add("Operator");
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
        return userName;
    }
    public void setUserName(String userName) throws DTOException {
        if (userName.length() >= 2  && userName.length() <= 20)
            this.userName = userName;
        else
            throw new DTOException("Username er ikke i den talladt længde");
    }
    public String getIni() {
        return ini;
    }
    public void setIni(String ini) throws DTOException {
        if (ini.length() >= 2  && ini.length() <= 4)
            this.ini = ini;
        else
            throw new DTOException("Initialer er ikke i den talladt længde");
    }
    public String getCpr() {
        return cpr;
    }
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) throws DTOException {
        for (Object test : roles.toArray()) {
            if (!possible_roles.contains(test)) {
                throw new DTOException("Rolle " + test.toString() + " er ikke en godkendt rolle");
            }
        }
        this.roles = roles;
    }

    public void addRole(String role) throws DTOException {
        if (!possible_roles.contains(role)) {
            throw new DTOException("Rolle " + role + " er ikke en godkendt rolle");
        }
        this.roles.add(role);
    }
    /**
     *
     * @param role
     * @return true if role existed, false if not
     */
    public boolean removeRole(String role){
        return this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
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