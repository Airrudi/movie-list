package nl.ruudclaassen.movie_list.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import nl.ruudclaassen.movie_list.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Role getRoleById(int id) {
		return em.find(Role.class, id);
	};
}