package nl.ruudclaassen.movie_list.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.exceptions.NoQueryResultException;
import nl.ruudclaassen.movie_list.model.Login;
import nl.ruudclaassen.movie_list.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public User getUserByUsername(String username) {
		Login login = this.getLoginByUsername(username);
		
		// TODO: Q: Does this actually work?
		return login.getUser();
	}
	
	@Override
	public Login getLoginByUsername(String username){
		Query q = em.createQuery("SELECT l FROM Login l WHERE username = :username");
		q.setParameter("username", username);
		
		try{
			return (Login) q.getSingleResult();
		} catch(NoResultException nre){			
			throw new NoQueryResultException("No matching rows found in table: 'Login'");
		}		
	}

	@Override	
	public User saveUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO: Add status field (enum) to users
		Query q = em.createQuery("SELECT u FROM User u");		
		
		try{
			return q.getResultList(); 
		} catch(NoResultException nre){			
			throw new NoQueryResultException("No users found");
		}	
	}

	@Override
	public User getUserByUUID(String uuid) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.uuid = :uuid");
		q.setParameter("uuid", uuid);
		
		try{
			return (User) q.getSingleResult();
		} catch(NoResultException nre){			
			throw new NoQueryResultException("No matching rows found in table: 'Login'");
		}		
	}	
}
