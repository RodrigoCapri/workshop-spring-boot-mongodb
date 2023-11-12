package com.rodrigocapri.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigocapri.workshopmongo.domain.User;
import com.rodrigocapri.workshopmongo.dto.UserDTO;
import com.rodrigocapri.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users") //Caminho para ser acessado
public class UserResource {

	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) //Ou @GetMapping
	public ResponseEntity< List<UserDTO> > findAll(){
		
		List<User> list= service.findAll(); //Lista original
		//Convertendo cada objeto da lista original para um DTO
		List<UserDTO> listDTO = list.stream().map( x -> new UserDTO(x) ).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}") //Ou @GetMapping
	public ResponseEntity< UserDTO > findById(@PathVariable String id){ //Casar o atributo com a requisição
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body( new UserDTO(obj) ); 
	}
	
}
