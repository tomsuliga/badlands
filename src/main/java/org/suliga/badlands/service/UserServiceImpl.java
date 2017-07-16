package org.suliga.badlands.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suliga.badlands.dao.AuthorityDao;
import org.suliga.badlands.dao.UserDao;
import org.suliga.badlands.model.Authorities;
import org.suliga.badlands.model.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public Iterable<Users> listUsers() {
		return userDao.findAll();
	}
	
	@Override
	@Transactional
	public void addUser(Users users, Authorities authorities) {
		users.hashPassword();
		userDao.save(users);
		authorityDao.save(authorities);
	}
}

