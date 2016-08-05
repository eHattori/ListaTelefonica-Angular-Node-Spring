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
public class AcessoBancoAlterarTest {

	@Test
	public void alterar () {
		ContatoDTO dto = new ContatoDTO();
		dto.setNome("Larissa teste update");
		dto.setTelefone("18-9999999");
		dto.set_id("57a4130f552efaa22b2c91bc");
		
		ListaTelefonicaDAO dao = new ListaTelefonicaDAO(Constantes.TABLE_CONTATOS);
		dao.alterar(dto);
	}
}
