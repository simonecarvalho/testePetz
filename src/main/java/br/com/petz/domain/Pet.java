package br.com.petz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@NoArgsConstructor
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	private String tipo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public Pet(Integer id, String nome, String tipo, Cliente cliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pet{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", tipo='" + tipo + '\'' +
				", cliente=" + cliente +
				'}';
	}
}