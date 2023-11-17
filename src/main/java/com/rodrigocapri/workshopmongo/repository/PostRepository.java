package com.rodrigocapri.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rodrigocapri.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //Qual dominio e o tipo da ID
	//Todas as funções de CRUD já incluino pelo MongoRepository
}
