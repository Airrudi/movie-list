package nl.ruudclaassen.movie_list.data.external.tmdb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import nl.ruudclaassen.movie_list.model.external.tmdb.MovieDb;

@Component
@Path("/movie/")
@Produces("application/json")
public interface MovieResource {
 
    @GET
    @Path("{id}")
    @Produces("application/json")
    public MovieDb getMovieById(@PathParam("id") int id);
}