package org.suliga.badlands.service;

import org.suliga.badlands.model.Authorities;
import org.suliga.badlands.model.Users;

public interface UserService {
	public Iterable<Users> listUsers();
	public void addUser(Users users, Authorities authorities);
}
