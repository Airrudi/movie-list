package nl.ruudclaassen.movie_list.service;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.UserDao;
import nl.ruudclaassen.movie_list.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public void addToDone(Media media) {
		// TODO Auto-generated method stub

	}

	public void addToTodo(Media media) {
		// TODO Auto-generated method stub

	}

	public void addToWishList() {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

}
