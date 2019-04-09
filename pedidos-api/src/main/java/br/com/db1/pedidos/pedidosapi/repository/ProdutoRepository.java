package br.com.db1.pedidos.pedidosapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.domain.entity.ProdutoStatus;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Iterable<Produto> findByStatus(ProdutoStatus status);
	
}
