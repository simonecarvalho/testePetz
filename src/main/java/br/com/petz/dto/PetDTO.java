package br.com.petz.dto;

import br.com.petz.domain.Pet;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Data
@Builder
public class PetDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;

	private String tipo;

	private Integer clienteId;


	@Override
	public String toString() {
		return "PetDTO{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", tipo='" + tipo + '\'' +
				", clienteId=" + clienteId +
				'}';
	}
}