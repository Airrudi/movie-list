package nl.ruudclaassen.movie_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.RoleDao;
import nl.ruudclaassen.movie_list.model.Role;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDao roleDao;

	@Override
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

}
