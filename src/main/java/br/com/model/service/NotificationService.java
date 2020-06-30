package br.com.model.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.model.dto.NotificationDTO;
import br.com.model.persistence.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	public List<NotificationDTO> getUserNotification(Integer id) {
		List<NotificationDTO> notRead = notificationRepository.findByUserIdAndRead(id, true).stream()
				.map(n -> modelMapper.map(n, NotificationDTO.class))
				.collect(Collectors.toList());
		
		List<NotificationDTO> read = notificationRepository.findTop5ByUserIdAndRead(id, false, Sort.by("date").descending()).stream()
				.map(n -> modelMapper.map(n, NotificationDTO.class))
				.collect(Collectors.toList());
		
		return Stream.concat(notRead.stream(), read.stream()).collect(Collectors.toList());
			
	}
}
