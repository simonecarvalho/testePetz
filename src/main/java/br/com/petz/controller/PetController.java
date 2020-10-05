package br.com.petz.controller;

import java.net.URI;
import java.util.List;

import br.com.petz.dto.PetDTO;
import br.com.petz.services.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/pet")
public class PetController {

	private PetService petService;

	public PetController(PetService service){
		petService = service;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PetDTO>> buscarTodos() {
		List<PetDTO> petDTOList = petService.buscarTodos();
		return ResponseEntity.ok().body(petDTOList);
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<PetDTO> buscarPorId(@PathVariable Integer id) {
		PetDTO petDTO = petService.buscarPorId(id);
		return ResponseEntity.ok().body(petDTO);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> cadastrar(@RequestBody PetDTO petDTO){
		PetDTO petDTONovo = petService.salvar(petDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(petDTONovo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody PetDTO petDTO, @PathVariable Integer id){
		petService.editar(petDTO, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		petService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
