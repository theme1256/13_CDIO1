package dal;


import dto.PasswordGen;
import dto.UserDTO;

import java.io.*;
import java.util.List;
import java.util.Scanner;


public class UserDAO implements IUserDAO {

    public static void main(String[] args) {
        UserDAO hej = new UserDAO();
        UserStore userStore = new UserStore();



        try {
            userStore.loadUsers();
        } catch ( DALException e){
            System.out.println("Fejl");
        }try {
            hej.createUser();
        } catch ( DALException e){
            System.out.println("Fejl");
        }

    }



    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void createUser() throws DALException {

        UserDTO user = new UserDTO();
        UserStore userStore = new UserStore();
        PasswordGen psgen = new PasswordGen();
        Scanner scan = new Scanner(System.in);

        System.out.println("Indstast ID");
        int id = scan.nextInt();
        System.out.println("Indstast Brugernavn");
        String username = scan.next();
        System.out.println("Indtast Rolle");
        String role = scan.next();
        System.out.println("Indstast Initialer");
        String ini = scan.next();
        System.out.println("Indstast Password");
        String password = psgen.passwordGen(psgen.lowerCase+psgen.upperCase+psgen.cifre+psgen.symbols);
        System.out.println("Dit password er: "+ password);
        user.setUserId(id);
        user.setUserName(username);
        user.addRole(role);
        user.setIni(ini);
        user.setPassword(password);
        userStore.data.add(user);
        userStore.saveUsers(userStore);


    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }











}
