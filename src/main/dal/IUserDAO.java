package dal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;

public interface IUserDAO {
    UserDTO getUser(int userId) throws DALException;
    ArrayList<UserDTO> getUserList();
    void updateUser(int userID, UserDTO user) throws DALException, ClassNotFoundException, SQLException;
    void createUser(UserDTO user) throws DALException, SQLException, ClassNotFoundException;
    void deleteUser(int userId) throws DALException, ClassNotFoundException, SQLException;

    public class DALException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }
    }
}