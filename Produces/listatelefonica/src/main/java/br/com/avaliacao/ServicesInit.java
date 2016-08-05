package br.com.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan( "br.com.avaliacao" )
public class ServicesInit{

	/**
	 * 
	 * M�todo respons�vel pela inicializa��o do servi�o
	 *
	 * @param args
	 *
	 * @author Larissa Bonif�cio Roder <larissaroder@gmail.com>
	 * @since 2 de ago de 2016 23:17:09
	 * @version 1.0
	 */
	public static void main( String[ ] args ) {
		SpringApplication.run( ServicesInit.class, args );
	}

}
