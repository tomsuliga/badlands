package org.suliga.badlands.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.suliga.badlands.model.Authorities;
import org.suliga.badlands.model.Users;
import org.suliga.badlands.service.AuthorityService;
import org.suliga.badlands.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping({"/","/index","/home"})
	public String getHome(Model model) {
		return "home";
	}
	
	@GetMapping("/newgame")
	public String newGame(Model model) {
		return "newgame";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login"; // Spring handles proper redirect to previous page
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		return "redirect:/home";
	}
	
	@GetMapping("/listusers")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("authorities", authorityService.listAuthorities());
		return "listusers";
	}
	
	@GetMapping("/adduser")
	public String addUser(Model model) {
		model.addAttribute("user", new Users());
		return "adduser";
	}
	
	@PostMapping("/adduser")
	public String addUserPost(Model model, Users users, String test1) {
		System.out.println("post users=" + users);
		System.out.println("post test1=" + test1);
		if (users.getPassword() != null && users.getUsername() != null) {
			users.setEnabled(true);
			userService.addUser(users, new Authorities(users.getUsername(), "ROLE_USER"));
		}
		return "redirect:listusers";
	}
	
	@GetMapping("/403")
	public String accessDenied403(Model model) {
		return "403";
	}
}









