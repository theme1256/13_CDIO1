package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOdb implements IUserDAO {

    String host = "primary.folkmann.it";
    String port = "3306";
    String username = "CDIO";
    String password = "y1NzaOYI08FrdqzX";
    String database = "/DTU_CDIO";

    //Do not edit these variables
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://"
            + host + ":" + port + database+"?characterEncoding=latin1";


    private ArrayList<UserDTO> users = new ArrayList<>();

    public UserDAOdb() {


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
    public UserDTO getUser(int userId) {

        int id = userId;

        UserDTO tmp = new UserDTO();

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM Userlist WHERE UserID='" + id + "'";


            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            resultSet.next();
            tmp.setIni(resultSet.getString("INI"));
            tmp.setUserId(resultSet.getInt("UserID"));
            tmp.setPassword(resultSet.getString("userPS"));
            tmp.setCpr(resultSet.getString("CPR"));
            tmp.setUserName(resultSet.getString("UserName"));

            connection.close();

            return tmp;


        } catch (SQLException | UserDTO.DTOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmp;
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
            e.printStackTrace();
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

    // rightPad method pads short attributenames and values to same columnwidth
    public static String rightPad(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }

}