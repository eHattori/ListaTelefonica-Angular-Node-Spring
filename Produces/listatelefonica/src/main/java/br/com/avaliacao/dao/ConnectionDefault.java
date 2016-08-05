package br.com.avaliacao.dao;

import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import br.com.avaliacao.util.Constantes;

/**
 * 
 * Classe que representa a conexão com o banco
 *
 *
 * @author Larissa Bonifácio Roder <larissaroder@gmail.com>
 * @since 2 de ago de 2016 23:24:07
 * @version 1.0
 */
public abstract class ConnectionDefault{
	private static Logger LOGGER = LogManager.getLogger( ConnectionDefault.class.getName() );

	protected final String ip = Constantes.IP_MONGO;
	protected final Integer port = Constantes.PORTA_MONGO;
	protected final String base = Constantes.DATABASE;
	protected Mongo mongo;
	public DB database;

	public ConnectionDefault(){
		super();
	}

	public void createConnection() {
		try {
			mongo = new MongoClient( this.ip, this.port );
			this.database = mongo.getDB( this.base );
		} catch ( UnknownHostException e ) {
			LOGGER.error( "ConnectionDefault - Erro ao estabelecer conexao com a base: " + e.getMessage() );
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if ( mongo != null ) {
			mongo.close();
		}
	}

}
