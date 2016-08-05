package br.com.avaliacao.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import br.com.avaliacao.dto.ContatoDTO;

/**
 * 
 * Classe que representa os acessos ao banco de dados
 *
 *
 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
 * @since 2 de ago de 2016 23:45:46
 * @version 1.0
 */
public final class ListaTelefonicaDAO extends ConnectionDefault {

	private static Logger LOGGER = LogManager.getLogger(ListaTelefonicaDAO.class);

	private String TABLE_NAME;
	private final String COLUMN_ID = "_id";
	private final String COLUMN_NOME = "nome";
	private final String COLUMN_TELEFONE = "telefone";

	public ListaTelefonicaDAO(String table) {
		TABLE_NAME = table;
	}

	/**
	 * 
	 * Método responsável por inserir contato no banco
	 *
	 * @param dto
	 *
	 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
	 * @since 2 de ago de 2016 23:32:39
	 * @version 1.0
	 */
	public void inserir(ContatoDTO dto) {
		LOGGER.info("<<<INICIO>>>");

		createConnection();

		try {
			DBCollection collection = database.getCollection(TABLE_NAME);

			BasicDBObject documento;
			documento = new BasicDBObject();

			documento.put(COLUMN_NOME, dto.getNome());
			documento.put(COLUMN_TELEFONE, dto.getTelefone());
			collection.insert(documento);

			LOGGER.info("Informações inseridas com sucesso.");

			LOGGER.info("<<<FIM>>>");
		} catch (Exception ex) {
			LOGGER.error("Erro ao inserir lista de arquivos: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	/**
	 * 
	 * Método responsável por Alterar um documento
	 *
	 * @param dto
	 *
	 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
	 * @since 2 de ago de 2016 23:35:12
	 * @version 1.0
	 */
	public void alterar(ContatoDTO dto) {
		LOGGER.info("<<<INICIO>>>");

		createConnection();

		try {
			DBCollection collection = database.getCollection(TABLE_NAME);

			BasicDBObject documento = new BasicDBObject();
			documento.put(COLUMN_ID, new ObjectId(dto.get_id()));
			documento.put(COLUMN_NOME, dto.getNome());
			documento.put(COLUMN_TELEFONE, dto.getTelefone());

			BasicDBObject query = new BasicDBObject().append(COLUMN_ID, new ObjectId(dto.get_id()));
			DBCursor cursor = collection.find(query);
			collection.update(cursor.next(), documento);
			LOGGER.info("Informações alteradas com sucesso.");

			LOGGER.info("<<<FIM>>>");
		} catch (Exception ex) {
			LOGGER.error("Erro ao alterar registro: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	/**
	 * 
	 * Método responsável por listar os arquivos persistidos
	 *
	 *
	 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
	 * @since 2 de ago de 2016 23:39:58
	 * @version 1.0
	 */
	public List<ContatoDTO> selecionarTodos(){
		LOGGER.info("<<<INICIO>>>");

		createConnection();
		DBCollection collection = database.getCollection(TABLE_NAME);

		// Recuperando as informações
		ContatoDTO contato = null;
		List<ContatoDTO> listaArquivos = new ArrayList<ContatoDTO>();
		for (DBObject document : collection.find()) {
			contato = new ContatoDTO();
			BasicDBObject basic = (BasicDBObject) document;
			contato.set_id(basic.getString(COLUMN_ID));
			contato.setNome(basic.getString(COLUMN_NOME));
			contato.setTelefone(basic.getString(COLUMN_TELEFONE));
			listaArquivos.add(contato);
		}
		// Fechando a conexão.
		closeConnection();

		LOGGER.info("<<<FIM>>>");
		return listaArquivos;
	}

	/**
	 * 
	 * Método responsável por Excluir um documento
	 *
	 * @param id
	 *
	 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
	 * @since 2 de ago de 2016 23:35:12
	 * @version 1.0
	 */
	public void excluir(ContatoDTO dto) {
		LOGGER.info("<<<INICIO>>>");

		createConnection();

		try {
			DBCollection collection = database.getCollection(TABLE_NAME);

			BasicDBObject documento = new BasicDBObject();
			documento.put(COLUMN_ID, new ObjectId(dto.get_id()));
			documento.put(COLUMN_NOME, dto.getNome());
			documento.put(COLUMN_TELEFONE, dto.getTelefone());

			collection.remove(documento);

			LOGGER.info("Informações excluidas com sucesso.");

			LOGGER.info("<<<FIM>>>");
		} catch (Exception ex) {
			LOGGER.error("Erro ao excluir registro: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeConnection();
		}
	}

}
