package br.com.db1.pedidos.pedidosapi.domain.dto;

import java.util.Objects;

import br.com.db1.pedidos.pedidosapi.domain.entity.ProdutoStatus;

public class ProdutoDTO {
	
	private String codigo;
	private String nome;
	private Double valor;
	private ProdutoStatus status;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String codigo, String nome, Double valor, ProdutoStatus status) {
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.status = status;
	}
 
	


	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome, valor, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProdutoDTO)) {
			return false;
		}
		ProdutoDTO other = (ProdutoDTO) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nome, other.nome)
				&& Objects.equals(valor, other.valor) && status == other.status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public ProdutoStatus getStatus() {
		return status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setStatus (ProdutoStatus status) {
		this.status = status;
	}
	
	

}
