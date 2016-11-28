package nl.ruudclaassen.movie_list.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.model.UserMediaStatus;

@Repository
public class UserMediaStatusDaoImpl implements UserMediaStatusDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public UserMediaStatus save(UserMediaStatus userMediaStatus){
		em.persist(userMediaStatus);
		return userMediaStatus;
	}
}
