package api;


import dal.IUserDAO;
import dal.UserDAO_DB;
import dal.UserDAO_File;
import dto.UserDTO;
import dto.UserSubmit;
import funk.IUserFunk;
import funk.UserFunk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ParamConverter;
import java.sql.SQLException;

@Path("userAdmin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    private IUserFunk funk = new UserFunk(new UserDAO_DB());


    @Path("create")
    @POST
    public Response addUser (UserSubmit submit) {

        try {
            UserDTO user = funk.createUser(submit.getUserID(), submit.getUsername(), submit.getIni(), submit.getCpr());
            if(submit.isPharmaceut()){
                funk.addRole(user,"Pharmacist");
            }
            if(submit.isAdmin()){
                funk.addRole(user,"Admin");
            }
            if(submit.isLaborant()){
                funk.addRole(user, "Laborant");
            }
            if(submit.isProduktionsleder()){
                funk.addRole(user,"Produktionsleder");
            }
            funk.storeUser(user);

            return Response.ok().build();
        } catch (UserDTO.DTOException | SQLException | IUserDAO.DALException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("update")
    @POST
    public Response updateUser(UserSubmit submit) {

        try {

            UserDTO user = funk.createUser(submit.getUserID(), submit.getUsername(), submit.getIni(), submit.getCpr());

            if(submit.isPharmaceut()){
                funk.addRole(user,"Pharmacist");
            }
            if(submit.isAdmin()){
                funk.addRole(user,"Admin");
            }
            if(submit.isLaborant()){
                funk.addRole(user, "Laborant");
            }
            if(submit.isProduktionsleder()){
                funk.addRole(user,"Produktionsleder");
            }

            funk.updateUser(submit.getUserID(), user);

            return Response.ok().build();

        } catch (SQLException | UserDTO.DTOException | IUserDAO.DALException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("delete")
    @POST
    public  Response deleteUser(UserSubmit submit){
        try {
            int userID = submit.getUserID();

            funk.deleteUser(userID);
            return Response.ok().build();
        } catch (IUserDAO.DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("getUser")
    @GET
    public Response getUser(@QueryParam("userID") int ID){
        try {

            UserDTO user = funk.getUser(ID);
            return Response.status(Response.Status.OK).entity(user).build();

        } catch (IUserDAO.DALException | SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("getUsers")
    @GET
    public Response getUsers() {
        return Response.status(Response.Status.OK).entity(funk.getUsers()).build();

    }
}
