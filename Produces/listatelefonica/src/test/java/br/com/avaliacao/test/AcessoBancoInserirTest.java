package br.com.avaliacao.test;

import org.junit.Test;

import br.com.avaliacao.dao.ListaTelefonicaDAO;
import br.com.avaliacao.dto.ContatoDTO;
import br.com.avaliacao.util.Constantes;

/**
 * 
 * Classe que representa Teste para manipulação de banco
 *
 *
 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
 * @since 5 de ago de 2016 02:36:11
 * @version 1.0
 */
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
