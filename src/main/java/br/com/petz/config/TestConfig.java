package br.com.petz.config;

import br.com.petz.services.BancoDeDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class TestConfig {
	
	@Autowired
	private BancoDeDadosService bancoDeDadosService;
	
	@Bean
	public boolean inicializarBancoDeDados() {
		bancoDeDadosService.inicializarBancoDeDados();
		return true;
	}
}
