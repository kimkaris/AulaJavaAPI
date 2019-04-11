package br.com.db1.pedidos.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.db1.pedidos.pedidosapi.domain.entity.PedidoItem;


public interface PedidoItemRepository extends JpaRepository <PedidoItem, Long>{
}
