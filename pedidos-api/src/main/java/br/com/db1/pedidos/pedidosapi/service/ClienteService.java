package br.com.db1.pedidos.pedidosapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.pedidos.pedidosapi.domain.dto.ClienteDTO;
import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusCliente;
import br.com.db1.pedidos.pedidosapi.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteDTO> getAll() {
		
		Iterable<Cliente> clientesDatabase = clienteRepository.findAll();
		Iterator<Cliente> iterator = clientesDatabase.iterator();
		
		List<ClienteDTO> clientes = new ArrayList<>();
		while(iterator.hasNext()) {
			Cliente next = iterator.next();
			ClienteDTO clienteDTO = new ClienteDTO(next.getId(), next.getNome(), next.getCpf(), next.getStatus());
			clientes.add(clienteDTO);
		}
		return clientes;
		
	}
	public List<ClienteDTO> getAtivos() {
		
		Iterable<Cliente> clientesDatabase = clienteRepository.findByStatus(StatusCliente.ATIVO);
		Iterator<Cliente> iterator = clientesDatabase.iterator();
		
		List<ClienteDTO> clientes = new ArrayList<>();
		while(iterator.hasNext()) {
			Cliente next = iterator.next();
			ClienteDTO clienteDTO = new ClienteDTO(next.getId(), next.getNome(), next.getCpf(), next.getStatus());
			clientes.add(clienteDTO);
		}
		return clientes;
		
	}
	
	public List<ClienteDTO> getInativos() {
		
		Iterable<Cliente> clientesDatabase = clienteRepository.findByStatus(StatusCliente.INATIVO);
		Iterator<Cliente> iterator = clientesDatabase.iterator();
		
		List<ClienteDTO> clientes = new ArrayList<>();
		while(iterator.hasNext()) {
			Cliente next = iterator.next();
			ClienteDTO clienteDTO = new ClienteDTO(next.getId(), next.getNome(), next.getCpf(), next.getStatus());
			clientes.add(clienteDTO);
		}
		return clientes;
		
	}
	
	public ClienteDTO salvar (ClienteDTO dto) {
		Cliente cliente = new Cliente(dto.getNome(), dto.getCpf());
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return this.clienteToDto(clienteSalvo);
	}
	
	private ClienteDTO clienteToDto(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getStatus());
	}
	
	
	public ClienteDTO alterar(Long id, ClienteDTO body) {
		Cliente clienteDatabase = clienteRepository.getOne(id);
		clienteDatabase.setNome(body.getNome());
		clienteDatabase.setCpf(body.getCpf());
		clienteRepository.save(clienteDatabase);
		return this.clienteToDto(clienteDatabase); 
		
	}
	public void delete(Long id) {
	Cliente clienteDatabase = clienteRepository.getOne(id);
	clienteDatabase.marcarComoExcluido();
	clienteRepository.save(clienteDatabase);
	}

	}

