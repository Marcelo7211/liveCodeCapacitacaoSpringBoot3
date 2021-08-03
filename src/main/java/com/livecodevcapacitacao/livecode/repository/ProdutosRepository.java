package com.livecodevcapacitacao.livecode.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.livecodevcapacitacao.livecode.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	//TODO criar metodos abstrados personalizados.
	
	/**
	 * Method query JPA
	 * 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 * */
	public List<Produtos> findAllByNomeProdutoContainingIgnoreCase(String nome);
	
	public List<Produtos> findAllByPrecoLessThanEqual(BigDecimal preco);
	
	
	/**
	 * Query anotation JPA
	 * 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
	 * */
	@Query(value = "select * from tb_produtos where disponivel = :valor", nativeQuery = true)
	public List<Produtos> pegarTodosAtivos(@Param("valor") boolean valor);
 }
