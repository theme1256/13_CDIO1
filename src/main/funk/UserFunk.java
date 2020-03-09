package funk;

import dal.IUserDAO;
import dal.UserDAO;
import dto.UserDTO;

import java.util.ArrayList;

public class UserFunk implements IUserFunk {
    private IUserDAO data;

    private PasswordController psController;
    public UserFunk() {
      data = new UserDAO();
      psController = new PasswordController();

    }


    @Override
    public UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setIni(ini);
        user.setCpr(cpr);
        user.setPassword(psController.passwordGen());

        return user;
    }

    @Override
    public void addRole(UserDTO user, String role) throws UserDTO.DTOException {
        user.addRole(role);
    }

    @Override
    public void storeUser(UserDTO user) throws IUserDAO.DALException {
        this.data.createUser(user);
    }


    @Override
    public ArrayList<UserDTO> getUsers() throws IUserDAO.DALException {

       return data.getUserList();

    }

    @Override
    public UserDTO getUser(int userID) throws IUserDAO.DALException {

        UserDTO user = data.getUser(userID);

        return user;
    }

    @Override
    public void updateUser(int oldID, int userID, String userName, String ini, String cpr) {

    }

    @Override
    public void deleteUser(int userID) throws IUserDAO.DALException {

        data.deleteUser(userID);

    }
}
