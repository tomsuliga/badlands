package org.suliga.badlands.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.suliga.badlands.model.Users;

public interface UserDao extends PagingAndSortingRepository<Users, Long> {
	
}