package br.com.db1.pedidos.pedidosapi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_cliente", length = 30, nullable = false)
	private StatusCliente status;
	
	protected Cliente() {}
	
	
	
	//CONSTRUTOR
	public Cliente(String nome, String cpf) {
		///VERIFICADORES
		Checker.notNull(nome, "nome do cliente");
		Checker.notNull(cpf, "cpf do cliente");
		Checker.checkCpf(cpf);
		
		
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.status = StatusCliente.ATIVO;
	}

	
	
	public void setNome(String nome) {
		Checker.notNull(nome, "nome do cliente");
		this.nome = nome;
	}
	
	
	public void setCpf(String cpf) {
		Checker.notNull(cpf, "cpf do cliente");
		Checker.checkCpf(cpf);
		this.cpf = cpf;
		

	}
	
	
	public Long getId() {
		return this.id;
	}
	
	
	public StatusCliente getStatus() {
		return this.status;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	//
	
	
	public void inativar() {
		if (!StatusCliente.ATIVO.equals(this.status)) {
			throw new RuntimeException("Cliente está " + this.status);
		}
		
		this.status = StatusCliente.INATIVO;
	}
	
	
	
	
	public boolean isAtivo() {
		return StatusCliente.ATIVO.equals(this.status);
	}



	public void marcarComoExcluido() {
		this.status = StatusCliente.EXCLUIDO;
		
	}
	
}
