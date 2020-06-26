package br.com.model.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.model.dto.LoginDTO;
import br.com.model.dto.UserDTO;
import br.com.model.service.LoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/simple")
	@ResponseBody
	public Map<String, Object> login (@RequestBody LoginDTO loginData) {
		return loginService.login(loginData);
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@ResponseBody
	public UserDTO getUser (@PathVariable Integer id) {
		return loginService.getUser(id);
	}
	
}
