package br.com.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.model.dto.LoginDTO;
import br.com.model.util.Translator;

@Service
public class LoginService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${apimodel.auth.url}")
	String authUrl;
	
	@Value("${apimodel.auth.authorization}")
	String authAuthorization;
	
	@Value("${apimodel.auth.grantType}")
	String authGrantType;
	
	public Map<String, Object> login (LoginDTO user) {
		
		String url = authUrl + "/oauth/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + authAuthorization);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("username", user.getEmail());
		body.add("password", user.getPassword());
		body.add("grant_type", authGrantType);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
		
	    ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

	    if (!result.getStatusCode().is2xxSuccessful()) {
	    	throw new RuntimeException(Translator.toLocale("login.failed"));    
	    }
	    return result.getBody();
	}
}
