package br.com.db1.pedidos.pedidosapi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;


@Entity
@Table(name = "item")
public class PedidoItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//foreign key produto
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id")
	private Produto produto;
	//
	
	@Column(name = "quantidade", nullable = false, precision = 16, scale = 3)
	private Double quantidade;
	
	@Column(name = "valor_unit", nullable = false, precision = 16, scale = 3)
	private Double valor_unit;
	
	//foreign key pedido
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;
	//
	
	
	
	
	//CONSTRUCTOR
	public PedidoItem(Pedido pedido, Produto produto, Double quantidade) {
		//VERIFICADORES
		Checker.notNull(produto, "produto");
		Checker.notNull(quantidade, "quantidade");
		Checker.biggerThanZero(quantidade, "quantidade");
		//
		
		if (!produto.isAtivo()) {
			throw new RuntimeException("Produto " + produto.getNome() + " est� " + produto.getStatus());
		}
		
		this.produto = produto;
		this.pedido = pedido;
		this.quantidade = quantidade;
		this.valor_unit = produto.getValor();
	}
	
	
	
	
	//GETTERS
	public Produto getProduto() {
		return this.produto;
	}
	
	public Double getQuantidade() {
		return this.quantidade;
	}
	
	public Double getValor() {
		return this.valor_unit;
	}
	
	public Double getValorTotal() {
		return this.valor_unit * this.quantidade;
	}
	//
	
}
