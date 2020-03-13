package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_DB implements IUserDAO {
    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIO";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1";

    public UserDAO_DB() {
    }

    @Override
    public void createUser(UserDTO user) throws DALException, SQLException, ClassNotFoundException {
        Class.forName(this.driver);

        String sqlManipulation = "INSERT Userlist VALUES ('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getIni() + "', '" + user.getCpr() + "', '" + user.getPassword() + "')";

        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlManipulation);

        for(String role : user.getRoles()){
            sqlManipulation ="INSERT UserRoles VALUES( "+ user.getUserId() +" , \"" + role + "\")";
            statement.executeUpdate(sqlManipulation);
        }
        connection.close();
    }

    @Override
    public void updateUser(int userID, UserDTO user) throws DALException, ClassNotFoundException, SQLException {
        Class.forName(this.driver);

        String sqlManipulation = "UPDATE Userlist SET UserID = " + user.getUserId() + ", UserName = '" + user.getUserName() + "', INI = '" + user.getIni() + "', CPR = '" + user.getCpr() + "', userPS = '" + user.getPassword() + "' WHERE UserID = " + userID;

        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlManipulation);

        sqlManipulation = "DELETE FROM UserRoles WHERE UserID='" + user.getUserId() + "'";
        statement.executeUpdate(sqlManipulation);

        for(String role : user.getRoles()){
            sqlManipulation ="INSERT UserRoles VALUES( "+ user.getUserId() +" , \"" + role + "\")";
            statement.executeUpdate(sqlManipulation);
        }
        connection.close();
    }


    @Override
    public UserDTO getUser(int userId) throws NullPointerException {
        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM Userlist WHERE UserID='" + userId + "'";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            if(resultSet.next()) {
                UserDTO tmp = new UserDTO();

                tmp.setIni(resultSet.getString("INI"));
                tmp.setUserId(resultSet.getInt("UserID"));
                tmp.setPassword(resultSet.getString("userPS"));
                tmp.setCpr(resultSet.getString("CPR"));
                tmp.setUserName(resultSet.getString("UserName"));

                String getUserRole = "SELECT * FROM UserRoles WHERE UserID='" + tmp.getUserId() + "'";
                Statement s2 = connection.createStatement();
                ResultSet rs = s2.executeQuery(getUserRole);

                while (rs.next()){
                    tmp.addRole(rs.getString("Role"));
                }
                rs.close();
                s2.close();

                connection.close();
                return tmp;
            } else {
                return null;
            }
        } catch (SQLException | UserDTO.DTOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<UserDTO> getUserList() {
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();

        try {
            Class.forName(this.driver);

            String sqlStatement;

            sqlStatement = "SELECT * FROM Userlist";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                UserDTO tmp = new UserDTO();
                tmp.setIni(resultSet.getString("INI"));
                tmp.setUserId(resultSet.getInt("UserID"));
                tmp.setPassword(resultSet.getString("userPS"));
                tmp.setCpr(resultSet.getString("CPR"));
                tmp.setUserName(resultSet.getString("UserName"));


                sqlStatement = "SELECT * FROM UserRoles WHERE UserID='" + tmp.getUserId() + "'";
                Statement s2 = connection.createStatement();
                ResultSet rs = s2.executeQuery(sqlStatement);

                while (rs.next()){
                    tmp.addRole(rs.getString("Role"));
                }
                rs.close();
                s2.close();

                users.add(tmp);
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException | UserDTO.DTOException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void deleteUser(int userId) throws DALException, ClassNotFoundException, SQLException {
        Class.forName(this.driver);

        String sqlManipulation;

        sqlManipulation = "DELETE FROM Userlist WHERE UserID='" + userId + "'";

        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlManipulation);
        connection.close();
    }
}