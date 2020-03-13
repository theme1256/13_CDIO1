package dal;

import dto.UserDTO;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAO_FileTest {

    @Test
    public void createGetDeleteUser() {
        IUserDAO dao = new UserDAO_File();
        try {
            UserDTO user = new UserDTO();
            user.setUserId(11);
            user.setUserName("Test");
            user.setCpr("123456-1234");
            user.setIni("TEST");
            user.setPassword("password");
            List<String> roles = new ArrayList<String>();
            roles.add("Admin");
            user.setRoles(roles);
            dao.createUser(user);

            UserDTO test = dao.getUser(11);
            assertSame("Brugernavn er ikke ens", user.getUserName(), test.getUserName());
            assertSame("CPR er ikke ens", user.getCpr(), test.getCpr());
            assertSame("INI er ikke ens", user.getIni(), test.getIni());
            assertSame("Password er ikke ens", user.getPassword(), test.getPassword());
            assertSame("Roler er ikke ens", user.getRoles(), test.getRoles());

            dao.deleteUser(11);
        } catch (UserDTO.DTOException | IUserDAO.DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createGetUpdateDeleteUser() {
        IUserDAO dao = new UserDAO_File();
        try {
            UserDTO user = new UserDTO();
            user.setUserId(11);
            user.setUserName("Test");
            user.setCpr("123456-1234");
            user.setIni("TEST");
            user.setPassword("password");
            List<String> roles = new ArrayList<String>();
            roles.add("Admin");
            user.setRoles(roles);
            dao.createUser(user);

            UserDTO test = dao.getUser(11);
            assertSame("Brugernavn er ikke ens", user.getUserName(), test.getUserName());
            assertSame("CPR er ikke ens", user.getCpr(), test.getCpr());
            assertSame("INI er ikke ens", user.getIni(), test.getIni());
            assertSame("Password er ikke ens", user.getPassword(), test.getPassword());
            assertSame("Roler er ikke ens", user.getRoles(), test.getRoles());

            test.setUserId(22);
            test.setUserName("Tester");
            dao.updateUser(user.getUserId(), test);

            UserDTO test2 = dao.getUser(test.getUserId());
            assertSame("Update: ID er ikke ens", test.getUserId(), test2.getUserId());
            assertSame("Update: Brugernavn er ikke ens", test.getUserName(), test2.getUserName());

            dao.deleteUser(11);
        } catch (UserDTO.DTOException | IUserDAO.DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
