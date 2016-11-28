package nl.ruudclaassen.movie_list.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import nl.ruudclaassen.movie_list.model.Login;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Login findByUsername(String username) {
		Query q = em.createQuery("SELECT l FROM Login l WHERE l.username = :username");
		q.setParameter("username", username);
		
		//TODO: Try catch
		return (Login) q.getSingleResult();
	}

}
