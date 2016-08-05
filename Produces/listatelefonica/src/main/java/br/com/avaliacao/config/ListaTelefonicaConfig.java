package br.com.avaliacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.avaliacao.dao.ListaTelefonicaDAO;
import br.com.avaliacao.util.Constantes;

@Configuration
public class ListaTelefonicaConfig {

	@Bean
	public ListaTelefonicaDAO listaTelefonicaDAO() {
		return new ListaTelefonicaDAO(Constantes.TABLE_CONTATOS);
	}
}
