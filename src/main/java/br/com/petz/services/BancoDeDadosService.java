package br.com.petz.services;

import java.util.Arrays;

import br.com.petz.domain.Cliente;
import br.com.petz.domain.Pet;
import br.com.petz.enums.TipoCliente;
import br.com.petz.repositories.ClienteRepository;
import br.com.petz.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BancoDeDadosService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public void inicializarBancoDeDados() {
		Cliente cliente = new Cliente(null, "Maria da Silva", "maria.silva@email.com", "969.564.050-83", TipoCliente.PESSOAFISICA, "91234-5678");
		Pet pet1 = new Pet(null, "Luke", "Cachorro", cliente);
		Pet pet2 = new Pet(null, "Mel", "Gato", cliente);
		cliente.getPets().addAll(Arrays.asList(pet1,pet2));

		Cliente cliente2 = new Cliente(null, "José de Souza", "jose@email.com", "203.832.330-54", TipoCliente.PESSOAFISICA, "98765-4321");
		Pet pet3 = new Pet(null, "Luna", "Cachorro", cliente2);
		cliente2.getPets().addAll(Arrays.asList(pet3));

		clienteRepository.saveAll(Arrays.asList(cliente,cliente2));
		petRepository.saveAll(Arrays.asList(pet1,pet2,pet3));
	}
}
