package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.exceptions.NoQueryResultException;
import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Media;
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

	public Movie getMovieByUUID(String uuid) {
		Query q = em.createQuery("SELECT m FROM Movie m WHERE m.uuid = :uuid");
		q.setParameter("uuid", uuid);		
		
		try{
			@SuppressWarnings("unchecked")
			Movie movie = (Movie) q.getSingleResult();
			return movie;
			
		} catch(NoResultException nre){			
			throw new NoQueryResultException("Movie not found");			
		}				
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

	@Override
	public List<Movie> getFreshMoviesByUser(User user) {		
		
		Query q = em.createQuery("SELECT m FROM Movie m WHERE m NOT IN :judgedMovies");
		q.setParameter("judgedMovies", user.getJudgedMovies().keySet());
		
		try{
			@SuppressWarnings("unchecked")
			List<Movie> freshMovies = q.getResultList();
			return freshMovies;
			
		} catch(NoResultException nre){
			throw new NoQueryResultException("No matching rows found in table: 'Genre'");			
		}
	}
	
	@Override
	public List<Movie> getJudgedMoviesByUser(User user) {
//		Query q = em.createQuery("SELECT ums FROM UserMediaStatus ums WHERE user_id = :userId");
//		q.setParameter("userId", user.getId());
//		
//		try{
//			List<Movie> judgedMovies = q.getResultList();
//			return judgedMovies;
//			
//		} catch(NoResultException nre){
//			throw new NoQueryResultException("No matching rows found in table: 'Genre'");			
//		}
		return null;
	}
	
	

	

}
