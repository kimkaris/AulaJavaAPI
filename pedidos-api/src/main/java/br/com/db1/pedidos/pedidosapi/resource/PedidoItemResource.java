package br.com.db1.pedidos.pedidosapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.pedidos.pedidosapi.domain.dto.PedidoItemDTO;
import br.com.db1.pedidos.pedidosapi.service.PedidoItemService;

@RestController
@RequestMapping(value = "/api/itens")
public class PedidoItemResource {

	@Autowired
	private PedidoItemService pedidoitemService;
	
	
	@PostMapping
	public PedidoItemDTO post(@RequestBody PedidoItemDTO body) {
		System.out.println(body);
		return body;
	}
}
