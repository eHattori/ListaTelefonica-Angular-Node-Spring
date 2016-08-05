package br.com.avaliacao.test;

import org.junit.Test;

import br.com.avaliacao.dao.ListaTelefonicaDAO;
import br.com.avaliacao.dto.ContatoDTO;
import br.com.avaliacao.util.Constantes;

public class AcessoBancoInserirTest {

	@Test
	public void inserir () {
		ContatoDTO dto = new ContatoDTO();
		dto.setNome("Larissa Roder");
		dto.setTelefone("18-9999999");
		
		ListaTelefonicaDAO dao = new ListaTelefonicaDAO(Constantes.TABLE_CONTATOS);
		dao.inserir(dto);
	}
}
