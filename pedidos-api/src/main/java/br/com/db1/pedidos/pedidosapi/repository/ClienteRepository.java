package br.com.db1.pedidos.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusCliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		
//	Cliente findByCpf(String cpf);
	
	Iterable<Cliente> findByStatus(StatusCliente status);
}
