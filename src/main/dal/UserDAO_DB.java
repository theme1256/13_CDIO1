package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO_DB implements IUserDAO {
    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIO";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + host + ":" + port + database+"?characterEncoding=latin1";

    private ArrayList<UserDTO> users = new ArrayList<>();

    public UserDAO_DB() {
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        int a = user.getUserId();
        String b = user.getUserName();
        String c = user.getIni();
        String d = user.getCpr();
        String e = user.getPassword();

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "INSERT Userlist VALUES ('" + a + "', '" + b + "', '" + c + "', '" + d + "', '" + e + "')";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();
        } catch (Exception k) {
            k.printStackTrace();
        }
    }

    @Override
    public void updateUser(int userID, UserDTO user) throws DALException {

    }


    @Override
    public UserDTO getUser(int userId) throws NullPointerException {
        int id = userId;

        UserDTO tmp = new UserDTO();

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM Userlist WHERE UserID='" + id + "'";


            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

           if(resultSet.next()) {
               tmp.setIni(resultSet.getString("INI"));
               tmp.setUserId(resultSet.getInt("UserID"));
               tmp.setPassword(resultSet.getString("userPS"));
               tmp.setCpr(resultSet.getString("CPR"));
               tmp.setUserName(resultSet.getString("UserName"));

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
        users = new ArrayList<UserDTO>();
        try {
            Class.forName(driver);

            String sqlStatement;

            sqlStatement = "SELECT * FROM Userlist";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                UserDTO tmp = new UserDTO();
                tmp.setIni(resultSet.getString("INI"));
                tmp.setUserId(resultSet.getInt("UserID"));
                tmp.setPassword(resultSet.getString("userPS"));
                tmp.setCpr(resultSet.getString("CPR"));
                tmp.setUserName(resultSet.getString("UserName"));

                users.add(tmp);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException | UserDTO.DTOException e) {
            System.out.println("HLLOA");
            // e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        int a = userId;

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "DELETE FROM Userlist WHERE userID='" + a + "'";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}