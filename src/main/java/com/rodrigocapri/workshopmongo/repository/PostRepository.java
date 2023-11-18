package com.rodrigocapri.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rodrigocapri.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //Qual dominio e o tipo da ID
	//Todas as funções de CRUD já incluino pelo MongoRepository
	
	//Exemplo de Query methods
	//O Spring data irá ontar pra gente a consulta
	//Retorna os posts cujo o titulo contenha a string informada
	//Ignorando as letras maiusculas e minusculas
	List<Post> findByTitleContainingIgnoreCase(String text); 
	
}
