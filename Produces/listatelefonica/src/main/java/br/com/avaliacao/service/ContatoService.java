package br.com.avaliacao.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.google.gson.Gson;

import br.com.avaliacao.dao.ListaTelefonicaDAO;
import br.com.avaliacao.dto.ContatoDTO;

/**
 * 
 * Classe que representa a gera��o dos servi�os
 *
 *
 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
 * @since 3 de ago de 2016 00:28:18
 * @version 1.0
 */
@RestController
@RequestMapping("/contatos")
public class ContatoService {

	private static final Logger LOGGER = LogManager.getLogger(ContatoService.class);

	@Autowired
	ListaTelefonicaDAO dao;

	/**
	 * 
	 * M�todo respons�vel por listar os contatos cadastrados
	 *
	 * @return
	 *
	 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
	 * @since 3 de ago de 2016 00:28:37
	 * @version 1.0
	 */
	@RequestMapping(value = "/listar", method = { RequestMethod.GET })
	public ResponseEntity<String> listar() {
		LOGGER.info("<< Inicializando a listagem. >>");
		try {
			List<ContatoDTO> contatos = dao.selecionarTodos();
			if (contatos.size() > 0) {
				Gson gson = new Gson();
				return new ResponseEntity<String>(gson.toJson(contatos), HttpStatus.OK);
			}
		} catch (Exception e) {
			if (e instanceof ResourceAccessException) {
				return new ResponseEntity<String>("Erro!! N�o possui conex�o", HttpStatus.REQUEST_TIMEOUT);
			}
		}

		LOGGER.info("<< Listagem realizada com sucesso. >>");
		return new ResponseEntity<String>("N�o h� registros", HttpStatus.OK);
	}

	/**
	 * 
	 * M�todo respons�vel por inserir os contatos
	 *
	 * @return
	 *
	 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
	 * @since 3 de ago de 2016 00:28:37
	 * @version 1.0
	 */
	@RequestMapping(value = "/inserir", method = { RequestMethod.POST })
	public ResponseEntity<String> inserir(@RequestBody ContatoDTO dto) {
		LOGGER.info("<< Inicializando a inser��o. >>");
		try {
			dao.inserir(dto);
			return new ResponseEntity<String>("Contatos Inseridos", HttpStatus.OK);
		} catch (Exception e) {
			if (e instanceof ResourceAccessException) {
				return new ResponseEntity<String>("Erro!! N�o possui conex�o", HttpStatus.REQUEST_TIMEOUT);
			}
		}
		LOGGER.info("<< inser��o realizada com sucesso. >>");
		return new ResponseEntity<String>("N�o h� registros", HttpStatus.OK);
	}

	/**
	 * 
	 * M�todo respons�vel por alterar os contatos
	 *
	 * @return
	 *
	 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
	 * @since 3 de ago de 2016 00:28:37
	 * @version 1.0
	 */
	@RequestMapping(value = "/atualizar/", method = { RequestMethod.PUT })
	public String alterar(@RequestBody ContatoDTO dto) {
		LOGGER.info("<< Inicializando a inser��o. >>");
		try {
			dao.alterar(dto);
			return new ResponseEntity<Object>("Contatos Inseridos", HttpStatus.OK).toString();
		} catch (Exception e) {
			if (e instanceof ResourceAccessException) {
				return new ResponseEntity<Object>("Erro!! N�o possui conex�o", HttpStatus.REQUEST_TIMEOUT).toString();
			}
		}
		LOGGER.info("<< inser��o realizada com sucesso. >>");
		return new ResponseEntity<Object>("N�o h� registros", HttpStatus.OK).toString();
	}

	/**
	 * 
	 * M�todo respons�vel por excluir os contatos
	 *
	 * @return
	 *
	 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
	 * @since 3 de ago de 2016 00:28:37
	 * @version 1.0
	 */
	@RequestMapping(value = "/excluir/", method = { RequestMethod.DELETE })
	public String excluir(@RequestBody ContatoDTO dto) {
		LOGGER.info("<< Inicializando a exclusao. >>");
		try {
			//ContatoDTO dto = new ContatoDTO();
			//dto.set_id(id);
			dao.excluir(dto);
			return new ResponseEntity<Object>("Contatos Inseridos", HttpStatus.OK).toString();
		} catch (Exception e) {
			if (e instanceof ResourceAccessException) {
				return new ResponseEntity<Object>("Erro!! N�o possui conex�o", HttpStatus.REQUEST_TIMEOUT).toString();
			}
		}
		LOGGER.info("<< exclusao realizada com sucesso. >>");
		return new ResponseEntity<Object>("N�o h� registros", HttpStatus.OK).toString();
	}

}
