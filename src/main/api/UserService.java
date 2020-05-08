package api;


import dto.UserDTO;
import funk.IUserFunk;
import funk.UserFunk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("userAdmin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
public class UserService {
    private IUserFunk funk;

    @POST
    @Path("form")
    public String addUser (UserFunk user, @FormParam ("userID") int userID, @FormParam ("username") String userName, @FormParam ("ini")String ini, @FormParam ("cpr") String cpr) throws UserDTO.DTOException, SQLException {

        user.createUser(userID, userName, ini, cpr);
        return "bruger oprettet";
    }



}
