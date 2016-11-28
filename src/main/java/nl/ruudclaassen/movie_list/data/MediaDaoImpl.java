package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.exceptions.NoQueryResultException;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;

@Repository
public class MediaDaoImpl implements MediaDao {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public byte[] getImageByMediaUUID(String uuid){
		Query q = em.createQuery("SELECT m FROM Media m WHERE uuid = :uuid");
		q.setParameter("uuid", uuid);
		
		try{
			Media media = (Media) q.getSingleResult();
			return media.getImage();
			
		} catch(NoResultException nre){
			throw new NoQueryResultException("No matching rows found in table: 'Genre'");			
		}
	}

	@Override
	public List<Media> getItemsSeen(User user) {	
		Query q = em.createQuery("SELECT m FROM Media m WHERE user_id = :userId");
		q.setParameter("userId", user.getId());
		//q.setParameter("type", type);
		
		try{
			List<Media> media = q.getResultList();
			return media;
			
		} catch(NoResultException nre){
			throw new NoQueryResultException("No media found for current user");			
		}
	}

	@Override
	public List<Media> getItemsToSee(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Media getByUUID(String uuid) {
		// TODO: Cannot get from media so temp change to movie
		Query q = em.createQuery("SELECT m FROM Movie m WHERE m.uuid = :uuid");
		q.setParameter("uuid", uuid);
		
		try{
			Media media = (Media) q.getSingleResult();
			return media;
			
		} catch(NoResultException nre){
			throw new NoQueryResultException("Media item not found for this id");			
		}
		
		
	}

	@Override
	@Transactional
	public void addToJudged(Media media) {		
		em.persist(media);
	}

	@Override
	public void deleteMedia(String uuid) {
		Query q = em.createQuery("SELECT m FROM Movie m WHERE uuid = :uuid");
		q.setParameter("uuid", uuid);
		
		try{
			Movie movie = (Movie) q.getSingleResult();
			em.remove(movie);
			
		} catch(NoResultException nre){
			throw new NoQueryResultException("No movie was found with this uuid");			
		}
	};
	
	
	
	
	
}
