package domain;

import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/moviess")
public class MovieResources {

    private MovieService db = new MovieService();


    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)

    public List<Movie> getAll()
    {
        return db.getAll();

    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response Add (Movie movie){

        db.add(movie);
        return Response.ok(movie.getId()).build();
    }


}