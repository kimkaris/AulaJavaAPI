package br.com.db1.pedidos.pedidosapi.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;


@Entity
@Table(name = "pedido")
public class Pedido {

	private static final int QUANTIDADE_MAXIMA_ITENS = 10;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo", nullable = false, length = 50)
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30, nullable = false)
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PedidoItem> itens = new ArrayList<>();
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Historico> historico = new ArrayList<>();
	
	@Column(name = "statusData", nullable = false)
	private LocalDateTime statusData;
	
	
	
	
	//CONSTRUCTOR
	public Pedido(String codigo, Cliente cliente, StatusPedido status, List<PedidoItem> itens,
			List<Historico> historico, LocalDateTime statusData) {
		//VERIFICADORES
		Checker.notNull(cliente, "cliente");
		Checker.notNull(codigo, "código");
		this.verificarClienteAtivo();
		//
		
		
		this.codigo = codigo;
		this.cliente = cliente;
		this.novoHistoricoStatus();
	}
	//
	
	
	
	
	//METODOS
	public void adicionarItem(PedidoItem pedido, Produto produto, Double quantidade) {
		this.verificarStatusPedidoParaAlterar();
		
		if (this.itens.size() == QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Quantidade máxima de itens excedida.");
		}
		
//		produto = pedido.getProduto();
//		quantidade = pedido.getQuantidade();
		
		this.itens.add(new PedidoItem(this, produto, quantidade));
	}
	
	public void removerItem(Produto produto) {
		this.verificarStatusPedidoParaAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}
	
	public void faturar() {
		if (!StatusPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está " + this.status);
		}
		
		if (this.itens.size() == 0 || this.itens.size() > QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Pedido deve ter no mínimo 1 item e no máximo 10 itens. Quantidade atual: " + this.itens.size());
		}
		
		this.verificarClienteAtivo();
		
		this.status = StatusPedido.FATURADO;
		this.novoHistoricoStatus();
	}
	
	public void cancelar() {
		this.verificarStatusPedidoParaAlterar();
		this.status = StatusPedido.CANCELADO;
		this.novoHistoricoStatus();
	}
	
	public void reabrir() {
		if (!StatusPedido.CANCELADO.equals(this.status)) {
			throw new RuntimeException("Pedido está " + this.status);
		}
		
		this.status = StatusPedido.ABERTO;
		this.novoHistoricoStatus();
	}
	//
	
	
	
	
	//GETTERS
	public String getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public StatusPedido getStatus() {
		return status;
	}


	public List<PedidoItem> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public List<Historico> getHistorico() {
		return Collections.unmodifiableList(historico);
	}

	public LocalDateTime getStatusData() {
		return statusData;
	}
	//
	
	
	
	
	private void novoHistoricoStatus() {
		Historico historico = new Historico(this, this.status);
		this.historico.add(historico);
		this.statusData = historico.getData();
	}
	
	
	
	
	//VERIFICADORES
	private void verificarClienteAtivo() {
		if (!cliente.isAtivo()) {
			throw new RuntimeException("Cliente " + cliente.getNome() + " está inativo");
		}
	}
	
	private void verificarStatusPedidoParaAlterar() {
		if (!StatusPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está " + this.status);
		}
	}

}
