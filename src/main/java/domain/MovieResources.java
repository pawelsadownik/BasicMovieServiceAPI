package domain;

import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get (@PathParam("id") int id){

    Movie result = db.get(id);
    if (result == null){
        return Response.status(404).build();
    }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (@PathParam("id") int id, Movie m) {

        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        m.setId(id);
        db.update(result);
        return Response.ok().build();

    }

    @DELETE
    @Path("/{id}")

    public Response delete (@PathParam("id") int id) {

        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        db.update(result);
        return Response.ok().build();

    }


    // Wyswietlenie listy aktorow danego filmu
    @GET
    @Path("/{movieId}/actors")
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Actor> getActors(@PathParam("movieId") int movieId ){

        Movie result = db.get(movieId);
        if (result == null)
            return null;

        if (result.getActors() == null)
            result.setActors(new ArrayList<Actor>());

        return result.getActors();
    }


    // Dodawanie aktorow do filmu

    @POST
    @Path("/{id}/actors")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addActor(@PathParam("id") int movieId, Actor actor ){

        Movie result = db.get(movieId);
        if (result == null)
            return Response.status(404).build();

        if (result.getActors() == null)
            result.setActors(new ArrayList<Actor>());

        result.getActors().add(actor);
        return Response.ok().build();

    // Dodanie filmow do aktora


        // Wyswietlenie filmow danego aktora


    }
}