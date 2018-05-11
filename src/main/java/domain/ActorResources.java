package domain;

import services.ActorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/actors")
public class ActorResources {

    private ActorService db = new ActorService();



    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Actor> getAll()
    {

        return db.getAll();

    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response Add (Actor actor){

        db.add(actor);
        return Response.ok(actor.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorById (@PathParam("id") int id){

        Actor result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response update (@PathParam("id") int id, Actor a) {

        Actor result = db.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        a.setId(id);
        db.update(result);
        return Response.ok().build();



    }

    @DELETE
    @Path("/{id}")

    public Response delete (@PathParam("id") int id) {

        Actor result = db.get(id);
        if (result == null)
            return Response.status(404).build();

        db.update(result);
        return Response.ok().build();

    }

    // Wyswietlenie listy filmow danego aktora
    @GET
    @Path("/{actorId}/movies")
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Movie> getMovies(@PathParam("actorId") int actorId ){

        Actor result = db.get(actorId);
        if (result == null)
            return null;

        if (result.getActorMovies() == null)
            result.setActorMovies(new ArrayList<Movie>());

        return result.getActorMovies();
    }


    // Dodawanie filmu do aktora

    @POST
    @Path("/{id}/movies")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addMovieToActor(@PathParam("id") int actorId, Movie movie){

        Actor result = db.get(actorId);
        if (result == null)
            return Response.status(404).build();

        if (result.getActorMovies() == null)
            result.setActorMovies(new ArrayList<Movie>());

        result.getActorMovies().add(movie);
        return Response.ok().build();




    }







}