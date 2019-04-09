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
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo", length = 100, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "valor", precision = 16, scale = 2, nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30, nullable = false)
	private ProdutoStatus status;
	
	protected Produto() {}
	
	
	//CONSTRUCTOR
	public Produto(String codigo, String nome, Double valor) {
		//VERIFICADORES
		Checker.notNull(nome, "nome do produto");
		Checker.notNull(codigo, "código do produto");
		Checker.notNull(valor, "valor do produto");
		//
		
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.status = ProdutoStatus.ATIVO;
	}
	
	
	
	
	//GETTERS
	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public Double getValor() {
		return this.valor;
	}

	public ProdutoStatus getStatus() {
		return this.status;
	}
	//
	
	
	
	
	public void inativar() {
		if (!ProdutoStatus.ATIVO.equals(this.status)) {
			throw new RuntimeException("Produto está " + this.status);
		}
		
		this.status = ProdutoStatus.INATIVO;
	}
	
	public boolean isAtivo() {
		return ProdutoStatus.ATIVO.equals(this.status);
	}
	
	
	
	
}
