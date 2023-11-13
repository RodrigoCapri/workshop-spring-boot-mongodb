package com.rodrigocapri.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigocapri.workshopmongo.domain.User;
import com.rodrigocapri.workshopmongo.dto.UserDTO;
import com.rodrigocapri.workshopmongo.repository.UserRepository;
import com.rodrigocapri.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	//Quando declara um objeto usando esta anotação, o proprio spring vai instanciar o objeto
	//Mecanismo de injeção de dependencia automatica do Spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) );
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
