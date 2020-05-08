package funk;

import dal.IUserDAO;
import dal.UserDAO_DB;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserFunk implements IUserFunk {
    private IUserDAO data;

    private PasswordController psController;

    public UserFunk(IUserDAO dal) {
      this.data = dal;
      this.psController = new PasswordController();
    }


    @Override
    public UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException, SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setIni(ini);
        user.setCpr(cpr);
        user.setPassword(this.psController.passwordGen());

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
       return this.data.getUserList();
    }

    @Override
    public UserDTO getUser(int userID) throws IUserDAO.DALException, SQLException {
        UserDTO user = this.data.getUser(userID);

        return user;
    }

    @Override
    public void updateUser(int oldID, UserDTO user) throws IUserDAO.DALException, UserDTO.DTOException, SQLException, ClassNotFoundException {
        this.data.updateUser(oldID, user);
    }

    @Override
    public void deleteUser(int userID) throws IUserDAO.DALException, SQLException, ClassNotFoundException {
        this.data.deleteUser(userID);
    }
}
