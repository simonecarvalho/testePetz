package br.com.petz.controller;

import java.net.URI;
import java.util.List;

import br.com.petz.dto.ClienteDTO;
import br.com.petz.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {
	
	private ClienteService clienteService;

	public ClienteController(ClienteService service){
		clienteService = service;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<ClienteDTO> clienteDTO = clienteService.buscarTodos();
		return ResponseEntity.ok().body(clienteDTO);
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(clienteDTO);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> cadastrar( @RequestBody ClienteDTO clienteDTO){
		ClienteDTO clienteDTONovo = clienteService.salvar(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(clienteDTONovo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		clienteService.editar(clienteDTO, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
