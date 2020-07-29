package com.excilys.formation.java.cdb.service.implemented;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.dao.UserDao;
import com.excilys.formation.java.cdb.model.User;

@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	public User findbyId(Long id) {
		User user;
		try {
			user = userDao.findById(id).get();
		}catch(Exception e) {
			throw e;
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("Username is empty");
		}
 
		User user = userDao.findByUsername(username);
 
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
	}
	
	public List<User> listUsers() {
		return userDao.findAll();
	}
	
	public void createUser(User user) {
		userDao.save(user);
	}
	
	public void deleteUser(User user) {
		userDao.delete(user);
	}
}
