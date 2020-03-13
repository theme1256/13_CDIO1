package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void createUser(UserDTO user) throws DALException, SQLException, ClassNotFoundException {

        int id = user.getUserId();
        String b = user.getUserName();
        String c = user.getIni();
        String d = user.getCpr();
        String e = user.getPassword();
        List<String> roles = user.getRoles();

            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "INSERT Userlist VALUES ('" + id + "', '" + b + "', '" + c + "', '" + d + "', '" + e + "')";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            for(String temp : roles){

                sqlManipulation ="INSERT UserRoles VALUES( "+ id +" , \"" + temp + "\")";
                statement.executeUpdate(sqlManipulation);
            }


            connection.close();



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
           } else return null;


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