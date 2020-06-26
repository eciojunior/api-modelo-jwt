package br.com.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.model.dto.RegisterDTO;
import br.com.model.dto.UserDTO;
import br.com.model.exception.BusinessRunTimeException;
import br.com.model.persistence.entity.User;
import br.com.model.persistence.repository.UserRepository;
import br.com.model.util.Translator;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	public User getUser (Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public List<UserDTO> getAll () {
		return userRepository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
	}
	
	public void updateUser (UserDTO user, String userId) {
		if (user.getId() == null || !user.getId().equals(Integer.valueOf(userId))) {
			throw new BusinessRunTimeException("user.update.notAllowed");
		}
		User usr = modelMapper.map(user, User.class);
		User usrSave = userRepository.findById(user.getId()).get();
		usr.setAuthority(usrSave.getAuthority());
		usr.setPassword(usrSave.getPassword());
		userRepository.save(usr);
	}
	
	public void registerUser (RegisterDTO user) {
		try {
			User usr = modelMapper.map(user, User.class);
			usr.setAuthority("User");
			usr.setPassword(passwordEncoder.encode(user.getPassword()));
			usr.setAvailable(true);
			userRepository.save(usr);	
		} catch (Exception e ) {
			String msg = "user.register.failed";
			LOGGER.error(Translator.toLocale(msg), e);
			throw new BusinessRunTimeException(msg);
		}
	}
}
