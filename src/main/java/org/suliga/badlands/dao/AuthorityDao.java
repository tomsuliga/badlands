package org.suliga.badlands.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.suliga.badlands.model.Authorities;

public interface AuthorityDao extends PagingAndSortingRepository<Authorities, Long> {
	
}
