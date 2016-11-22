package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import nl.ruudclaassen.movie_list.model.Genre;

@Repository
public class GenreDaoImpl implements GenreDao{
	
	@PersistenceContext
    private EntityManager em;
	
	// TODO: Q: Return directly and suppress unchecked warnings?
	@SuppressWarnings("unchecked")
	public List<Genre> getGenres(){
		return em.createQuery("SELECT g FROM Genre g").getResultList();
	}
	
}
