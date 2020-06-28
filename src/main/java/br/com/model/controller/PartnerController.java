package br.com.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.model.dto.PartnerDTO;
import br.com.model.service.PartnerService;

@Controller
@RequestMapping("partner")
public class PartnerController {
	
	@Autowired
	PartnerService partnerService;
	
	@GetMapping("/all")
	@ResponseBody
	public List<PartnerDTO> login () {
		return partnerService.getAll();
	}
		
	@PostMapping("/register")
	@ResponseBody
	public void registerPartner(@RequestBody PartnerDTO ptr) {
		partnerService.savePartner(ptr);
	}

}
