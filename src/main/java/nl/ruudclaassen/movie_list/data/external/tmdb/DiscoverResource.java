package nl.ruudclaassen.movie_list.data.external.tmdb;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

import nl.ruudclaassen.movie_list.model.external.tmdb.MovieDb;


@Component
@Path("/discover/movie")
@Produces("application/json")
public interface DiscoverResource {
	
	@GET    
    public List<MovieDb> getMoviesByYear(
    		@QueryParam("api_key") String api_key,
    		@QueryParam("sort_by") String sortBy,
    		@QueryParam("primary_release_year") int year
    );
	
	@GET
    @Path("sort_by=popularity.desc&api_key=fa676cb99b28e7fcae03a052c3ddcd0c&primary_release_year={year}")
    @Produces("application/json")
    public List<MovieDb> getPopularMovies();
	
}