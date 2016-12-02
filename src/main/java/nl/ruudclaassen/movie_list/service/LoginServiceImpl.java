package nl.ruudclaassen.movie_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.LoginDao;
import nl.ruudclaassen.movie_list.model.Login;
import nl.ruudclaassen.movie_list.model.Role;
import nl.ruudclaassen.movie_list.model.User;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	RoleService roleService;
		
	@Override
	public Login findByUsername(String username) {
		return loginDao.findByUsername(username);
	}	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Load login from database (throw exception if not found)
		Login login = loginDao.findByUsername(username);
		
		if(login == null){
			throw new UsernameNotFoundException("Login not found");
		}
		
		return login;	
	}

	@Override
	@Transactional
	public Login saveLogin(User user, String password) {		
		Role role = roleService.getRoleById(1);
		Login login = new Login();
		
		login.setUsername(user.getEmail());
		login.setPassword(password);
		login.setRole(role);
		login.setUser(user);
		login.setEnabled(true);		
		
		loginDao.saveLogin(login);		
		
		return login;
	}
}
