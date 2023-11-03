package com.rodrigocapri.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigocapri.workshopmongo.domain.User;

@RestController
@RequestMapping(value="/users") //Caminho para ser acessado
public class UserResource {

	
	@RequestMapping(method = RequestMethod.GET) //Ou @GetMapping
	public ResponseEntity< List<User> > findAll(){
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		List<User> list= new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		
		return ResponseEntity.ok().body(list);
	}
	
}
