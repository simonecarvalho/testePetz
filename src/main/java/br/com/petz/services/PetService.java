package br.com.petz.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.petz.domain.Pet;
import br.com.petz.dto.ClienteDTO;
import br.com.petz.dto.PetDTO;
import br.com.petz.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	private PetRepository petRepository;

	@Autowired
	private ClienteService clienteService;

	public PetService(PetRepository repository){
		petRepository = repository;
	}

	public List<PetDTO> buscarTodos(){
		List<Pet> petList = petRepository.findAll();
		return petList.stream().map(pet -> PetDTO.builder()
				.id(pet.getId())
				.nome(pet.getNome())
				.tipo(pet.getTipo())
				.clienteId(pet.getCliente().getId())
				.build()
		).collect(Collectors.toList());
	}

	public PetDTO buscarPorId(Integer idPet) {
		Optional<Pet> pet = petRepository.findById(idPet);
		return pet.map(toPet -> PetDTO.builder()
				.id(toPet.getId())
				.nome(toPet.getNome())
				.tipo(toPet.getTipo())
				.clienteId(toPet.getCliente().getId())
				.build()).orElseThrow(() -> new RuntimeException(
				"Objeto não encontrado! Id: " + idPet + ", Tipo: " + Pet.class.getName()));
	}

	public PetDTO salvar(PetDTO petDTO) {
		Pet pet = petRepository.save(fromDTO(petDTO));
		return Optional.ofNullable(pet).map(toPet -> PetDTO.builder()
				.id(toPet.getId())
				.nome(toPet.getNome())
				.tipo(toPet.getTipo())
				.clienteId(toPet.getCliente().getId())
				.build()).orElseThrow(() -> new RuntimeException(
				"Não foi possível salvar"));
	}

	public PetDTO editar(PetDTO petDTO, Integer id) {
		petDTO.setId(id);
		Pet petFromDTO = fromDTO(petDTO);
		Pet petVindodoBanco = petRepository.findById(id).get();
		updateData(petVindodoBanco, petFromDTO);
		Pet petSalvo = petRepository.save(petVindodoBanco);
		return Optional.ofNullable(petSalvo).map(toPet -> PetDTO.builder()
				.id(toPet.getId())
				.nome(toPet.getNome())
				.tipo(toPet.getTipo())
				.clienteId(toPet.getCliente().getId())
				.build()).orElseThrow(() -> new RuntimeException(
				"Não foi possível editar"));
	}

	private void updateData(Pet newObj, Pet obj) {
		newObj.setNome(obj.getNome());
		newObj.setTipo(obj.getTipo());
		newObj.setCliente(obj.getCliente());
	}
	
	public void delete(Integer id) {
		buscarPorId(id);
		try {
			petRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Não é possível excluir o Pet.");
		}
	}

	public Pet fromDTO(PetDTO objDTO) {
		ClienteDTO clienteDTO = clienteService.buscarPorId(objDTO.getClienteId());
		return new Pet(objDTO.getId(), objDTO.getNome(), objDTO.getTipo(), clienteService.fromDTO(clienteDTO));
	}
}
