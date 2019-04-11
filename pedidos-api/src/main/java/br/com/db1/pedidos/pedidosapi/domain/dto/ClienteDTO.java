package br.com.db1.pedidos.pedidosapi.domain.dto;

import java.util.Objects;

import br.com.db1.pedidos.pedidosapi.domain.entity.StatusCliente;

public class ClienteDTO {
	private Long id;
	private String nome;
	private String cpf;
	private StatusCliente status;
	
	
	public ClienteDTO(Long id, String nome, String cpf, StatusCliente status) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
		}



	@Override
	public int hashCode() {
		return Objects.hash(id, cpf, nome, status);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ClienteDTO)) {
			return false;
		}
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(nome, other.nome) && status == other.status;
	}

	public Long getId() {
		return id;
		

	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

	public StatusCliente getStatus() {
		return status;
	}



	public void setStatus(StatusCliente status) {
		this.status = status;
	}
	
	
}
