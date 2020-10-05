package br.com.petz.dto;

import br.com.petz.domain.Cliente;
import br.com.petz.domain.Pet;
import br.com.petz.enums.TipoCliente;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Builder
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String email;

	private String cpfOuCnpj;

	private Integer tipo;

	private String telefone;

	private List<Pet> pets;

	@Override
	public String toString() {
		return "ClienteDTO{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", cpfOuCnpj='" + cpfOuCnpj + '\'' +
				", tipo=" + tipo +
				", telefone='" + telefone + '\'' +
				'}';
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}
}