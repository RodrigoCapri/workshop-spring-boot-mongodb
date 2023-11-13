package com.rodrigocapri.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.POST ) //Para inserir um novo usuario @PostMapping
	public ResponseEntity< Void > insert(@RequestBody UserDTO objDTO ){ //Casar o atributo com a requisição
		
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		//Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); //Retorna o codigo 201, quando se cria um novo recurso
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}") 
	public ResponseEntity< Void > delete(@PathVariable String id){ //Casar o atributo com a requisição
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();  //204 no content, não retorna nada
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}" ) //Para atualizar um registro
	public ResponseEntity< Void > update(@PathVariable String id, @RequestBody UserDTO objDTO ){ //Casar o atributo com a requisição
		
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();  //204 no content, não retorna nada
	}
	
}
