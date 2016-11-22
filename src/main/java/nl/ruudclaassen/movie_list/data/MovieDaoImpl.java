package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;

@Repository
public class MovieDaoImpl implements MovieDao {

	@PersistenceContext
    private EntityManager em;

	// TODO: Q: Why no warning in Intellij?
	public List<Movie> getMovies() {
		List<Movie> movies = em.createQuery("SELECT m FROM Movie m").getResultList();
		return movies;
	}

	public Movie getMovieById(String uuid) {
		Movie movie = em.find(Movie.class, uuid);
		return movie;
	}

	public List<Movie> getMoviesPerUser(User user) {
		//List<Movie> movies = em.createQuery("SELECT m FROM Movie m WHERE m.user").getResultList();
		return null;
	}

	public List<Movie> getMoviesPerGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Movie saveMovie(Movie movie) {
		em.persist(movie);
		return movie;
	}
	
	@Transactional
	public Movie updateMovie(Movie movie) {
		
		// TODO: Q: How to update only changed values?
		// TODO: Q: Missing values in the update movie not made empty?
		
//		Movie movie = em.find(Movie.class, movie.getId());		 
//		em.getTransaction().begin();
//		employee.setNickname("Joe the Plumber");
//		em.getTransaction().commit();
		
		return movie;
	}
	
	

	

}
