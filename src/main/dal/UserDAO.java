package dal;


import dto.UserDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements IUserDAO {

    public ArrayList<UserDTO> users = new ArrayList<>();


    public UserDAO() throws IUserDAO.DALException {
        loadUsers();
    }


    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return users;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        users.add(user);
        saveUsers();


    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

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
