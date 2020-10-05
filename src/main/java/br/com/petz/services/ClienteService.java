package br.com.petz.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.petz.domain.Cliente;
import br.com.petz.dto.ClienteDTO;
import br.com.petz.enums.TipoCliente;
import br.com.petz.repositories.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository repository){
		clienteRepository = repository;
	}

	public List<ClienteDTO> buscarTodos(){
		List<Cliente> clienteList = clienteRepository.findAll();
		return clienteList.stream().map(cliente -> ClienteDTO.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.email(cliente.getEmail())
				.cpfOuCnpj(cliente.getCpfOuCnpj())
				.tipo(cliente.getTipo().getCodigo())
				.telefone(cliente.getTelefone())
				.pets(cliente.getPets())
				.build()
		).collect(Collectors.toList());
	}

	public ClienteDTO buscarPorId(Integer idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		return cliente.map(toCliente -> ClienteDTO.builder()
				.id(toCliente.getId())
				.nome(toCliente.getNome())
				.email(toCliente.getEmail())
				.cpfOuCnpj(toCliente.getCpfOuCnpj())
				.tipo(toCliente.getTipo().getCodigo())
				.telefone(toCliente.getTelefone())
				.pets(toCliente.getPets())
				.build()
		).orElseThrow(() -> new RuntimeException(
				"Objeto não encontrado! Id: " + idCliente + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public ClienteDTO salvar(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.save(fromDTO(clienteDTO));
		return Optional.ofNullable(cliente).map(
				toCliente -> ClienteDTO.builder()
						.id(toCliente.getId())
						.nome(toCliente.getNome())
						.email(toCliente.getEmail())
						.cpfOuCnpj(toCliente.getCpfOuCnpj())
						.tipo(toCliente.getTipo().getCodigo())
						.telefone(toCliente.getTelefone())
						.build()
		).orElseThrow(() -> new RuntimeException(
				"Não foi possível salvar"));
	}

	public ClienteDTO editar(ClienteDTO clienteDTO, Integer id) {
		clienteDTO.setId(id);
		Cliente clienteFromDTO = fromDTO(clienteDTO);
		Cliente clienteVindodoBanco = clienteRepository.findById(id).get();
		updateData(clienteVindodoBanco, clienteFromDTO);
		Cliente clienteSalvo = clienteRepository.save(clienteVindodoBanco);
		return Optional.ofNullable(clienteSalvo).map(
				toCliente -> ClienteDTO.builder()
						.id(toCliente.getId())
						.nome(toCliente.getNome())
						.email(toCliente.getEmail())
						.cpfOuCnpj(toCliente.getCpfOuCnpj())
						.tipo(toCliente.getTipo().getCodigo())
						.telefone(toCliente.getTelefone())
						.build()
		).orElseThrow(() -> new RuntimeException(
				"Não foi possível editar"));
	}
	
	private void updateData(Cliente cliente, Cliente clienteFromDTO) {
		cliente.setNome(clienteFromDTO.getNome());
		cliente.setEmail(clienteFromDTO.getEmail());
		cliente.setCpfOuCnpj(clienteFromDTO.getCpfOuCnpj());
		cliente.setTipo(clienteFromDTO.getTipo());
		cliente.setTelefone(clienteFromDTO.getTelefone());
	}
	
	public void delete(Integer id) {
		buscarPorId(id);
		try {
			clienteRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new RuntimeException("Não é possível excluir o cliente.");
		}
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo().getCodigo()), clienteDTO.getTelefone());
	}
}