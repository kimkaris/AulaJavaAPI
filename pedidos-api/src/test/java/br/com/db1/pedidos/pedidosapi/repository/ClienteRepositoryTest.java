package br.com.db1.pedidos.pedidosapi.repository;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusCliente;
import br.com.db1.pedidos.pedidosapi.repository.ClienteRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Before
	public void before() {
		clienteRepository.deleteAll();
		Cliente cliente = new Cliente("Vencturim", "66600066611");
		clienteRepository.save(cliente);
	}
	
	@Test
	public void deveSalvarUmCliente() {
		long count = clienteRepository.count();
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void deveSalvarClienteAlterado() {
		Cliente clienteSalvo = clienteRepository.findByCpf("66600066611");
		clienteSalvo.inativar();
		clienteRepository.save(clienteSalvo);
		
		Cliente clienteAlterado = clienteRepository.findByCpf("66600066611");

		Assert.assertEquals(StatusCliente.INATIVO, clienteAlterado.getStatus());
		
	}
}
