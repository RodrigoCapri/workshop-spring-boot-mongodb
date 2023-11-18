package com.rodrigocapri.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigocapri.workshopmongo.domain.Post;
import com.rodrigocapri.workshopmongo.repository.PostRepository;
import com.rodrigocapri.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	//Quando declara um objeto usando esta anotação, o proprio spring vai instanciar o objeto
	//Mecanismo de injeção de dependencia automatica do Spring
	private PostRepository repo;
	
	public List<Post> findAll(){
		
		List<Post> list = repo.findAll();
		
		return list;
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) ); //Se for nulo dispara uma exceção
	}
	
	
}
