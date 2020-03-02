package funk;

import dto.UserDTO;

import java.util.ArrayList;

public class UserFunk implements IUserFunk {
    @Override
    public void createUser(int userID, String userName, String ini, String cpr) {

    }

    @Override
    public void addRole(int userID, String role) {

    }

    @Override
    public ArrayList<UserDTO> listUsers() {
        return null;
    }

    @Override
    public void updateUser(int oldID, int userID, String userName, String ini, String cpr) {

    }

    @Override
    public void deleteUser(int userID) {

    }
}
