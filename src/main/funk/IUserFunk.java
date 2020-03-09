package funk;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;

public interface IUserFunk {
    UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException;
    void addRole(UserDTO user, String role) throws UserDTO.DTOException;
    void storeUser(UserDTO user) throws IUserDAO.DALException;
    void resetRoles(UserDTO user);
    ArrayList<UserDTO> getUsers();
    UserDTO getUser(int userID) throws IUserDAO.DALException;
    UserDTO updateUser(int oldID, int userID, String userName, String ini, String cpr) throws IUserDAO.DALException, UserDTO.DTOException;
    void deleteUser(int userID) throws IUserDAO.DALException;
}
