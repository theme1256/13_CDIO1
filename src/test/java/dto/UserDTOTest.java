package dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDTOTest {
    @Test
    public void setUserIdCorrect() {
        UserDTO user = new UserDTO();
        try {
            user.setUserId(11);
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void setUserIdWrong() {
        UserDTO user = new UserDTO();
        try {
            user.setUserId(1);
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void setUserNameCorrect() {
        UserDTO user = new UserDTO();
        try {
            user.setUserName("Tester");
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void setUserNameWrong() {
        UserDTO user = new UserDTO();
        try {
            user.setUserName("T");
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void setIniCorrect() {
        UserDTO user = new UserDTO();
        try {
            user.setIni("TEST");
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void setIniWrong() {
        UserDTO user = new UserDTO();
        try {
            user.setIni("T");
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void setCpr() {
        UserDTO user = new UserDTO();
        try {
            user.setCpr("123456-1234");
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void setCprWrong() {
        UserDTO user = new UserDTO();
        try {
            user.setCpr("1234561234");
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addRoleCorrect() {
        UserDTO user = new UserDTO();
        try {
            user.addRole("Admin");
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void addRoleWrong() {
        UserDTO user = new UserDTO();
        String role = "Administrator";
        try {
            user.addRole("Administrator");
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void setPasswordCorrect() {
        UserDTO user = new UserDTO();
        try {
            user.setPassword("123456");
            assertTrue(true);
        } catch (UserDTO.DTOException e) {
            fail("Dette skulle ikke give en fejl");
        }
    }

    @Test
    public void setPasswordWrong() {
        UserDTO user = new UserDTO();
        try {
            user.setPassword("12345");
            fail("Dette skulle give en fejl");
        } catch (UserDTO.DTOException e) {
            assertTrue(true);
        }
    }
}
