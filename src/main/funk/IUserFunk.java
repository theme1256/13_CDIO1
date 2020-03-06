package funk;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;

public interface IUserFunk {
    UserDTO createUser(int userID, String userName, String ini, String cpr) throws UserDTO.DTOException;
    void addRole(UserDTO user, String role) throws UserDTO.DTOException;
    void storeUser(UserDTO user) throws IUserDAO.DALException;
    ArrayList<UserDTO> getUsers() throws IUserDAO.DALException;
    UserDTO getUser(int userID) throws IUserDAO.DALException;
    void updateUser(int oldID, int userID, String userName, String ini, String cpr);
    void deleteUser(int userID) throws IUserDAO.DALException;
}
