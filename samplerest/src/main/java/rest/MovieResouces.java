package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Movie;
import domain.Actor;
import domain.Comment;
import domain.services.ActorService;
import domain.services.MovieService;

@Path("/movie")
public class MovieResouces {
	
	private MovieService db = new MovieService();
	private ActorService dba = new ActorService();
	
	@GET  //Wyswietla wszystkie filmy
	@Path("/film")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAll(){
		return db.getAll();
	}
	
	@POST //Dodaj film
	@Path("/film")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Movie movie) {
		db.add(movie);
		return Response.ok(movie.getId()).build();
	}
	
	@GET //Wyswietl film o konkretnym id
	@Path("/film/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Movie result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	@PUT //Aktualizacja danych filmu
	@Path("/film/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Movie m) {
		Movie result = db.get(id);
		if(result==null)
			return Response.status(404).build();
		m.setId(id);
		db.update(m);
		return Response.ok().build();
	}
	
	@POST //dodaj komentarz do filmu
	@Path("/film/{movieId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("movieId") int movieId, Comment comment){
		Movie result = db.get(movieId);
		if(result==null)
			return Response.status(404).build();
		if(result.getComents()==null) 
			result.setComents(new ArrayList<Comment>());
		
		result.getComents().add(comment);
		return Response.ok().build();
	}
	
	@GET // Wyswietl komentarze filmu
	@Path("/film/{movieId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("movieId") int movieId){
		Movie result = db.get(movieId);
		if (result==null)
			return null;
		if (result.getComents()==null)
			result.setComents(new ArrayList<Comment>());
		return result.getComents();
	}
	
	@DELETE //Usun komentarz
	@Path("/film/{id}/comments/{commentId}")
	public Response delete(@PathParam("commentId") int id, @PathParam("id") int movieId) {
		Movie result = db.get(movieId);
		if(result==null)
			return Response.status(404).build();
		result.deleteComents(id);
		return Response.ok().build();
	}
	
	@GET  //Wyswietla wszystkich aktorów
	@Path("/actor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAllActors(){
		return dba.getAll();
	}
	
	@POST //Dodaj aktora
	@Path("/actor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Actor actor) {
		dba.add(actor);
		return Response.ok(actor.getId()).build();
	}
	
	@POST //dodaj aktora do filmu
	@Path("/actor/{actorId}/{movieId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addConnection(@PathParam("movieId") int movieId, @PathParam("actorId") int actorId ){
		Movie result = db.get(movieId);
		Actor resulta = dba.get(actorId);
		if(result==null || resulta==null)
			return Response.status(404).build();
		if(result.getActors()==null) 
			result.setActors(new ArrayList<String>());
		if(resulta.getMovies()==null) 
			resulta.setMovies(new ArrayList<String>());		
		result.getActors().add(resulta.getName() + " " + resulta.getSurname());
		resulta.getMovies().add(result.getTitle());
		return Response.ok().build();
	}
	
	@GET // Wyswietl aktorów filmu
	@Path("/film/{movieId}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getActors(@PathParam("movieId") int movieId){
		Movie result = db.get(movieId);
		if (result==null)
			return null;
		if (result.getActors()==null)
			result.setActors(new ArrayList<String>());
		return result.getActors();
	}
	
	@GET // Wyswietl komentarze filmu
	@Path("/actor/{actorId}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getMovies(@PathParam("actorId") int actorId){
		Actor result = dba.get(actorId);
		if (result==null)
			return null;
		if (result.getMovies()==null)
			result.setMovies(new ArrayList<String>());
		return result.getMovies();
	}
	
	
}
