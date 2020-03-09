package dal;


import dto.UserDTO;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserDAO implements IUserDAO {

    public ArrayList<UserDTO> users = new ArrayList<>();


    //Edit the variables below to match a specific setup
    String host = "primary.folkmann.it";
    String port = "3306";
    String username = "CDIO";
    String password = "y1NzaOYI08FrdqzX";
    String database = "/DTU_CDIO";

    //Do not edit these variables
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://" + host + ":" + port + database+"?characterEncoding=latin1";

    public UserDAO() {

        /*try {
            loadUsers();
        } catch (DALException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public void createUser(UserDTO user) throws DALException {

        users.add(user);
        saveUsers();

        int a = user.getUserId();
        String b = user.getUserName();
        String c = user.getIni();
        String d = user.getCpr();
        String e = user.getPassword();

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "INSERT userlist VALUES ('" + a + "', '"  + b + "', '" + c + "', '" + d + "', '" + e + "')";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (Exception k) {
            k.printStackTrace();
        }


    }



    @Override
    public UserDTO getUser(int userId) throws DALException {

        for (int i = 0; i < users.size(); i++){

            if(users.get(i).getUserId() == userId){

                return users.get(i);

            }
        }

        return null;
    }

    @Override
    public ArrayList<UserDTO> getUserList() throws DALException {
        return users;
    }



    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

       /* for (int i = 0; i < users.size(); i++){

            if(users.get(i).getUserId() == userId){

                users.remove(i);
                saveUsers();

            }
        } */
        int a = userId;

        try {
            Class.forName(driver);

            String sqlManipulation;

            sqlManipulation = "DELETE FROM userlist WHERE userID='"+a+"'";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private void saveUsers() throws IUserDAO.DALException {
        ObjectOutputStream oOS =null;
        try {
            FileOutputStream fOS = new FileOutputStream("Users");
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users);
        } catch (FileNotFoundException e) {
            throw new IUserDAO.DALException("Error locating file", e);
        } catch (IOException e) {
            throw new IUserDAO.DALException("Error writing to disk", e);
        } finally {
            if (oOS!=null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    throw new IUserDAO.DALException("Unable to close ObjectStream", e);
                }
            }
        }
    }



    private void loadUsers() throws IUserDAO.DALException {
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream("Users");
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof ArrayList){
                users = (ArrayList<UserDTO>) inObj;
            } else {
                throw new IUserDAO.DALException("Wrong object in file");
            }
        } catch (FileNotFoundException e) {
            //No problem - just returning empty userstore
        } catch (IOException e) {
            throw new IUserDAO.DALException("Error while reading disk!", e);
        } catch (ClassNotFoundException e) {
            throw new IUserDAO.DALException("Error while reading file - Class not found!", e);
        } finally {
            if (oIS!=null){
                try {
                    oIS.close();
                } catch (IOException e) {
                    throw new IUserDAO.DALException("Error closing pObjectStream!", e);
                }
            }
        }
    }











}
