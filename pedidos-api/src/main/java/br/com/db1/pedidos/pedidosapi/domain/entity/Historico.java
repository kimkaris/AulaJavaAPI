package br.com.db1.pedidos.pedidosapi.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;


@Entity
@Table(name = "historico")
public class Historico {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20, nullable = false)
	private StatusPedido status;
	
	@Column(name = "data", nullable = false)
	private LocalDateTime data ;
	
	//foreign key pedido
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;
	//
	
	
	
	//CONSTRUCTOR
	public Historico(Pedido pedido, StatusPedido status) {
		//VERIFICADOR
		Checker.notNull(status, "status");
		//
		
		this.pedido = pedido;
		this.status = status;
		this.data = LocalDateTime.now();
	}
	//
	
	
	
	//GETTERS + SETTERS
	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	//
	
	
}
