package br.com.avaliacao.test;

import java.util.List;

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
public class AcessoBancoSelecionarTest {

	@Test
	public void selecionar () {	
		ListaTelefonicaDAO dao = new ListaTelefonicaDAO(Constantes.TABLE_CONTATOS);
			List<ContatoDTO> contatos = dao.selecionarTodos();
			contatos.forEach(contato -> System.out.println(contato.get_id()));
		
	}
}
