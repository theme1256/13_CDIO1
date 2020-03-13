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


public class UserDAO_File implements IUserDAO {
    public ArrayList<UserDTO> users = new ArrayList<>();

    public UserDAO_File() {
        try {
            loadUsers();
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        for (int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getUserId() == userId){
                return this.users.get(i);
            }
        }

        throw new DALException("Brugeren blev ikke fundet");
    }

    @Override
    public ArrayList<UserDTO> getUserList() {
        return this.users;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        this.users.add(user);
        saveUsers();
    }

    @Override
    public void updateUser(int userID, UserDTO user) throws DALException {
        this.deleteUser(userID);
        this.createUser(user);
    }

    @Override
    public void deleteUser(int userId) throws DALException {
       for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUserId() == userId) {
                this.users.remove(i);
                saveUsers();
            }
        }
    }

    private void saveUsers() throws IUserDAO.DALException {
        ObjectOutputStream oOS =null;
        try {
            FileOutputStream fOS = new FileOutputStream("Users");
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(this.users);
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

    public void loadUsers() throws IUserDAO.DALException {
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream("Users");
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof ArrayList){
                this.users = (ArrayList<UserDTO>) inObj;
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