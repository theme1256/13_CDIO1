package api;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hello")
public class HelloService {

    @GET
    public String getHello(){
        return "hello";
    }

    @POST
    @Path("{name}")
    public String postHello(@PathParam("name") String name){
        /*Variablerne tages fra URL'en
         * Eksempel på HTTP kald: POST localhost:8080/Lektion10/rest/ingredient/4/sukker/45 */
        return "Hello " + name;
    }
}
