package edu.curso.agendacontato

import edu.curso.agendacontato.mapping.ContatoMapping
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AgendaContatoApplication {


//	@Bean
//	fun contatoMapping() : ContatoMapping {
//		return ContatoMapping()
//	}

}

fun main(args: Array<String>) {
	runApplication<AgendaContatoApplication>(*args)
}
