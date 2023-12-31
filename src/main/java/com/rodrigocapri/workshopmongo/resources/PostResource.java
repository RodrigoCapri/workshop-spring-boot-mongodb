package com.rodrigocapri.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigocapri.workshopmongo.domain.Post;
import com.rodrigocapri.workshopmongo.resources.util.URL;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/titlesearch") //Ou @GetMapping
	public ResponseEntity< List<Post> > findByTitle(@RequestParam(value = "text", defaultValue = "") String text){ //O valor vai ser passado como paramentro
		
		text = URL.decodeParam(text); //Decodificar o paramentro text
		
		List<Post> list = service.findByTitle(text); //Retorna a lista de post com o paramentro text ja decodificado
		
		return ResponseEntity.ok().body( list ); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/fullsearch") //Ou @GetMapping
	public ResponseEntity< List<Post> > fullSearch(
				@RequestParam(value = "text", defaultValue = "") String text,
				@RequestParam(value = "minDate", defaultValue = "") String minDate,
				@RequestParam(value = "maxDate", defaultValue = "") String maxDate
			){ //O valor vai ser passado como paramentro
		
		text = URL.decodeParam(text); //Decodificar o paramentro text
		Date min = URL.convertDate(minDate, new Date(0L)); //Date minima existente
		Date max = URL.convertDate(maxDate, new Date());  //Data atual da maquina
		
		List<Post> list = service.fullSearch(text, min, max); //Retorna uma lista de post com varios criterios de consulta
		
		return ResponseEntity.ok().body( list ); 
	}
	
}
