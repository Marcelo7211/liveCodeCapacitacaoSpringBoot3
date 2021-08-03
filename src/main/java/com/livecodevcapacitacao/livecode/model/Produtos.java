package com.livecodevcapacitacao.livecode.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity // neste momento estou informando ao JPA que eu preciso de uma tabela com os mesmos paramentros que minha classe
@Table(name = "tb_produtos") // Neste momento estou nomeando a tabela
public class Produtos {
	
	@Id // criando uma chave primária (PK)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_Increment (1, 2, 3 ....)
	private long id; // criando uma coluna com o nome de id do tipo bitInt
	
	@Size(min = 2, max = 255, message = "Valor minimo 2 e maximo 255")
	@NotBlank // não aceita nulo e nem " "
	private String nomeProduto; // criando uma coluna com o nome nome_produto do tipo varchar(255)
	
	@Min(value = 0, message = "No minimo 0,01 centavo")
	private BigDecimal preco;
	
	private boolean disponivel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
}
