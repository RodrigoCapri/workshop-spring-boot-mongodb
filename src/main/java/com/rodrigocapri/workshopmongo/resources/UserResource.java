package com.rodrigocapri.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigocapri.workshopmongo.domain.User;
import com.rodrigocapri.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users") //Caminho para ser acessado
public class UserResource {

	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) //Ou @GetMapping
	public ResponseEntity< List<User> > findAll(){
		
		List<User> list= service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
}
