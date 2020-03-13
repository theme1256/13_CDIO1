package funk;

import dal.IUserDAO;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserFunk {
    UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException, SQLException;
    void addRole(UserDTO user, String role) throws UserDTO.DTOException;
    void storeUser(UserDTO user) throws IUserDAO.DALException, SQLException, ClassNotFoundException;
    void resetRoles(UserDTO user);
    ArrayList<UserDTO> getUsers();
    UserDTO getUser(int userID) throws IUserDAO.DALException, SQLException;
    void updateUser(int oldID, UserDTO user) throws IUserDAO.DALException, UserDTO.DTOException, SQLException, ClassNotFoundException;
    void deleteUser(int userID) throws IUserDAO.DALException, SQLException, ClassNotFoundException;
}
