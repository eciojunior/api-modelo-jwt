package br.com.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.model.dto.RegisterDTO;
import br.com.model.dto.UserDTO;
import br.com.model.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserDTO> login () {
		return userService.getAll();
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PostMapping("/update")
	@ResponseBody
	public void updateUser(@RequestBody UserDTO user) {
		userService.updateUser(user);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public void registerUser(@RequestBody RegisterDTO user) {
		userService.registerUser(user);
	}
	
}