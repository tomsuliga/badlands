package org.suliga.badlands.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suliga.badlands.dao.AuthorityDao;
import org.suliga.badlands.model.Authorities;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public Iterable<Authorities> listAuthorities() {
		return authorityDao.findAll();
	}

	@Override
	public void addAuthority(Authorities authorities) {
		authorityDao.save(authorities);
	}
}
