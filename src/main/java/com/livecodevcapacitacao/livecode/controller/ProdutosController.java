package com.livecodevcapacitacao.livecode.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livecodevcapacitacao.livecode.model.Produtos;
import com.livecodevcapacitacao.livecode.repository.ProdutosRepository;

@RestController // informado ao springo que esta classe se trata de um controlador Rest
@RequestMapping("/produtos") //Preciso de definor uma uri/rota/caminho para este controlador
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll(){
		/*select * from tb_produtos*/
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable long id){
		return repository.findById(id).map(batata -> ResponseEntity.ok(batata))
				.orElse(ResponseEntity.badRequest().build());
		
	}
	
	@GetMapping("/por-nome/{nome}")
	public ResponseEntity<List<Produtos>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainingIgnoreCase(nome));
	}
	
	@GetMapping("/por-preco/{preco}")
	public ResponseEntity<List<Produtos>> getByPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(repository.findAllByPrecoLessThanEqual(preco));
	}
	
	@GetMapping("/isAtivo/{isAtivo}")
	public ResponseEntity<List<Produtos>> isAtivo(@PathVariable boolean isAtivo){
		return ResponseEntity.ok(repository.pegarTodosAtivos(isAtivo));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(produto));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	

}
