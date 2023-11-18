package com.rodrigocapri.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigocapri.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //Qual dominio e o tipo da ID
	//Todas as funções de CRUD já incluino pelo MongoRepository
	
	//Um método que retorna um Post com consulta @Query
	// sintaxe json -> { <field>: { $regex: /pattern/, $options: '<options>' } }
	// $regex: ?0 -> reutilizando o primeiro paramentro especificado no metodo
	// $options: 'i' -> case insensitive
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") 
	List<Post> searchTitle(String text);
	
	//Exemplo de Query methods
	//O Spring data irá ontar pra gente a consulta
	//Retorna os posts cujo o titulo contenha a string informada
	//Ignorando as letras maiusculas e minusculas
	List<Post> findByTitleContainingIgnoreCase(String text); 
	
	// { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	// { $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	// gte -> maior ou igual,  { field: { $gte: value } }
	// lte -> menor ou igual,  { field: { $lte: value } }
	@Query(" {"
			+ " $and: [ "
					+ "{ date: { $gte: ?1 } }, "
					+ "{ date: { $lte: ?2 } } , "
					+ "{ "
						+ "$or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] "
					+ "} "
				+ "] "
			+ "} ")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
