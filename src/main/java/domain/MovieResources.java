package domain;

import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/movies")
public class MovieResources {

    private MovieService dbService = new MovieService();


    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)

    public List<Movie> getAll()
    {

        return dbService.getAll();

    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response Add (Movie movie){

        dbService.add(movie);
        return Response.ok(movie.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById (@PathParam("id") int id){

    Movie result = dbService.get(id);
    if (result == null){
        return Response.status(404).build();
    }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response update (@PathParam("id") int id, Movie m) {

        Movie result = dbService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        m.setId(id);
        dbService.update(m);
        return Response.ok().build();


    }

    @DELETE
    @Path("/{id}")

    public Response delete (@PathParam("id") int id) {

        Movie result = dbService.get(id);
        if (result == null)
            return Response.status(404).build();

        dbService.delete(result);
        return Response.ok().build();

    }
    // Wyswietlenie komentarzy filmu

    @GET
    @Path("/{movieId}/comments")
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Comment> getComments(@PathParam("movieId") int movieId ){

        Movie result = dbService.get(movieId);
        if (result == null)
            return null;

        if (result.getComments() == null)
            result.setComments(new ArrayList<Comment>());

        return result.getComments();
    }

    // Dodawanie komentarzy do filmu

    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addComment(@PathParam("id") int movieId, Comment comment ) {

        Movie result = dbService.get(movieId);
        if (result == null)
            return Response.status(404).build();


        if (result.getComments() == null) {
            result.setComments(new ArrayList<>());
        }

        int commentId = result.getComments().size();
        comment.setId(commentId);

        result.getComments().add(comment);
        dbService.update(result);
        return Response.ok().build();
    }
// Wyswietlenie oceny filmu

    @GET
    @Path("/{movieId}/rate")
    @Consumes(MediaType.APPLICATION_JSON)

    public double getRate(@PathParam("movieId") int movieId ){

        Movie result = dbService.get(movieId);
        if (result == null)
            return 0;

        if (result.getRates() == null)
            result.setRates(new ArrayList<Rate>());

        return result.getRate();
    }
//Dodanie oceny filmu


    @POST
    @Path("/{id}/rate")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addRate(@PathParam("id") int movieId, Rate rate ) {

        Movie result = dbService.get(movieId);
        if (result == null)
            return Response.status(404).build();


        if (result.getRates() == null) {
            result.setRates(new ArrayList<>());
        }

        int rateId = result.getRates().size();


        result.getRates().add(rate);
        dbService.update(result);
        return Response.ok().build();
    }


    // Wyswietlenie listy aktorow danego filmu
    @GET
    @Path("/{movieId}/actors")
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Actor> getActors(@PathParam("movieId") int movieId ){

        Movie result = dbService.get(movieId);
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

    public Response addActor(@PathParam("id") int movieId, Actor actor){

        Movie result = dbService.get(movieId);
        if (result == null)
            return Response.status(404).build();

        if (result.getActors() == null)
            result.setActors(new ArrayList<Actor>());

        result.getActors().add(actor);
        return Response.ok().build();




    }
}