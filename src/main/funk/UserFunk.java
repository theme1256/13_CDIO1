package funk;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;

public class UserFunk implements IUserFunk {
    private IUserDAO store;

    public UserFunk() {
        //
    }


    @Override
    public UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setIni(ini);
        user.setCpr(cpr);
        return user;
    }

    @Override
    public void addRole(UserDTO user, String role) throws UserDTO.DTOException {
        user.addRole(role);
    }

    @Override
    public void storeUser(UserDTO user) throws IUserDAO.DALException {
        this.store.createUser(user);
    }

    @Override
    public ArrayList<UserDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO getUser(int userID) {
        return null;
    }

    @Override
    public void updateUser(int oldID, int userID, String userName, String ini, String cpr) {

    }

    @Override
    public void deleteUser(int userID) {

    }
}
