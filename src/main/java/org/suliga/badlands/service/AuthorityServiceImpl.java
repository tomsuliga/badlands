package org.suliga.badlands.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<String> uniqueAuthorities() {
		List<String> list = new ArrayList<>();
		list.add("USER");
		list.add("ADMIN");
		list.add("DBA");
		return list;
	}
}
