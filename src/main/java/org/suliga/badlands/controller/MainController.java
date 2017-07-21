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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suliga.badlands.model.Authorities;
import org.suliga.badlands.model.Users;
import org.suliga.badlands.service.AddImageService;
import org.suliga.badlands.service.AuthorityService;
import org.suliga.badlands.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private AddImageService addImageService;
	
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
		model.addAttribute("authorities", authorityService.uniqueAuthorities());
		return "adduser";
	}
	
	@PostMapping("/adduser")
	public String addUserPost(Model model, Users users, String authority) {
		System.out.println("post users=" + users);
		System.out.println("post authority=" + authority);
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
	
	@GetMapping("/font")
	public String getCustomFont(Model model) {
		return "font";
	}
	
	@GetMapping("/swagger")
	public String getSwagger() {
		//return "redirect:swagger-ui.html";
		return "redirect:swagger-ui.html#/main-rest-controller";
	}
	
	@GetMapping("/listimages")
	public String listImages(Model model) {
		return "listimages";
	}
	
	@PostMapping("/addimage")
	public String addImagePost(Model model, 
		@RequestParam("file1") MultipartFile file1,
    	@RequestParam("file2") MultipartFile file2,
    	@RequestParam("file3") MultipartFile file3,
        RedirectAttributes redirectAttributes) {
		addImageService.addImages(file1, file2, file3);
		return "redirect:listimages";
	}
	
	@GetMapping("/addimage")
	public String addImage(Model model) {
		return "addimage";
	}
}






