package funk;

import dto.UserDTO;

import java.util.ArrayList;

public interface IUserFunk {
    void createUser(int userID, String userName, String ini, String cpr);
    void addRole(int userID, String role);
    ArrayList<UserDTO> listUsers();
    void updateUser(int oldID, int userID, String userName, String ini, String cpr);
    void deleteUser(int userID);
}
