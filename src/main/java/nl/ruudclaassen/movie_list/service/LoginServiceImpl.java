package nl.ruudclaassen.movie_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.LoginDao;
import nl.ruudclaassen.movie_list.model.Login;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;
		
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
}
