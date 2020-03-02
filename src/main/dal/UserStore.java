package dal;

import dto.UserDTO;

import java.io.*;
import java.util.ArrayList;

public class UserStore {


public ArrayList<UserDTO> data = new ArrayList<>();




    void saveUsers(UserStore users) throws IUserDAO.DALException {
        ObjectOutputStream oOS =null;
        try {
            FileOutputStream fOS = new FileOutputStream("Users");
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users.data);
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



    UserStore loadUsers() throws IUserDAO.DALException {
        UserStore userStore = new UserStore();
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream("Users");
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof ArrayList){
                data = (ArrayList<UserDTO>) inObj;
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
        return userStore;
    }

}
