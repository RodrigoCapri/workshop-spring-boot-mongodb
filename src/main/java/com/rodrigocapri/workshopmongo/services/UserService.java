package com.rodrigocapri.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigocapri.workshopmongo.domain.User;
import com.rodrigocapri.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	//Quando declara um objeto usando esta anotação, o proprio spring vai instanciar o objeto
	//Mecanismo de injeção de dependencia automatica do Spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
}
