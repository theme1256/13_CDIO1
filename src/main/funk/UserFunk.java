package funk;

import dal.IUserDAO;
import dal.UserDAO_DB;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserFunk implements IUserFunk {
    private IUserDAO data;

    private PasswordController psController;

    public UserFunk() {
      data = new UserDAO_DB();
      psController = new PasswordController();
    }

    @Override
    public UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException, SQLException {
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
    public void storeUser(UserDTO user) throws IUserDAO.DALException, SQLException, ClassNotFoundException {
        this.data.createUser(user);
    }


    @Override
    public void resetRoles(UserDTO user) {
        user.removeRoles();
    }

    @Override
    public ArrayList<UserDTO> getUsers() {
       return data.getUserList();
    }

    @Override
    public UserDTO getUser(int userID) throws IUserDAO.DALException, SQLException {
        UserDTO user = data.getUser(userID);

        return user;
    }

    @Override
    public void updateUser(int oldID, UserDTO user) throws IUserDAO.DALException, UserDTO.DTOException {
        this.data.updateUser(oldID, user);
    }

    @Override
    public void deleteUser(int userID) throws IUserDAO.DALException {
        data.deleteUser(userID);
    }
}
