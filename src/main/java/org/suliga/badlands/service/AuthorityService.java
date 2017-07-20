package org.suliga.badlands.service;

import java.util.List;

import org.suliga.badlands.model.Authorities;

public interface AuthorityService {
	public Iterable<Authorities> listAuthorities();
	public void addAuthority(Authorities authorities);
	public List<String> uniqueAuthorities();
}
