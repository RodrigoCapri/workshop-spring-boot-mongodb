package com.rodrigocapri.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigocapri.workshopmongo.domain.Post;
import com.rodrigocapri.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts") //Caminho para ser acessado
public class PostResource {

	
	@Autowired
	private PostService service;
	
	@RequestMapping(method = RequestMethod.GET) //Ou @GetMapping
	public ResponseEntity< List<Post> > findAll(){ //Casar o atributo com a requisição
		
		List<Post> list = service.findAll();
		
		return ResponseEntity.ok().body( list ); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}") //Ou @GetMapping
	public ResponseEntity< Post > findById(@PathVariable String id){ //Casar o atributo com a requisição
		
		Post obj = service.findById(id);
		
		return ResponseEntity.ok().body( obj ); 
	}
	
}
