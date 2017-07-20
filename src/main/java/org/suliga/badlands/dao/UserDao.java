package org.suliga.badlands.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.suliga.badlands.model.Users;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserDao extends PagingAndSortingRepository<Users, Long> {
	List<Users> findByUsername(@Param("username") String username);
}
