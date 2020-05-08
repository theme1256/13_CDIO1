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
import java.sql.SQLException;

@Path("userAdmin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    private IUserFunk funk = new UserFunk(new UserDAO_File());

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
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

}
