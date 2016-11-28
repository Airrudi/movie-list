package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import nl.ruudclaassen.movie_list.exceptions.NoQueryResultException;
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

	public Genre getGenreByUuid(String uuid) {
		Query q = em.createQuery("SELECT g FROM Genre g WHERE uuid = :uuid");
		q.setParameter("uuid", uuid);
		
		try{
			return (Genre) q.getSingleResult();
		} catch(NoResultException nre){
			throw new NoQueryResultException("No matching rows found in table: 'Genre'");
		}
	}
	
}
