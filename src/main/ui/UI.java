package ui;

import dal.IUserDAO;

public interface UI {
    void start();
    void createUser();
    void listUsers() throws IUserDAO.DALException;
    void updateUser();
    void deleteUser() throws IUserDAO.DALException;
    void stop();
}
