package br.com.db1.pedidos.pedidosapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.domain.entity.ProdutoStatus;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
	Produto findByCodigo(String codigo);
	
	Iterable<Produto> findByStatus(ProdutoStatus status);
	
}
