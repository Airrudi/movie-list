package nl.ruudclaassen.movie_list.data.external.tmdb;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.annotations.Logging;

import nl.ruudclaassen.movie_list.model.external.tmdb.MovieDb;
import nl.ruudclaassen.movie_list.model.external.tmdb.core.ResultsPage;
import nl.ruudclaassen.movie_list.model.external.tmdb.keywords.KeywordMovie;


//@Component
@Path("/discover/movie")
@Produces("application/json")
@Logging
public interface DiscoverResource {
	
	@GET    
    public List<MovieDb> getMoviesByYear(
    		@QueryParam("api_key") String api_key,
    		@QueryParam("sort_by") String sortBy,
    		@QueryParam("primary_release_year") int year
    );
	
	@GET    
    @Produces("application/json")
    public ResultsPage<KeywordMovie> getPopularMovies(
    		@QueryParam("api_key") String api_key,
    		@QueryParam("sort_by") String sortBy    		
    );	
}