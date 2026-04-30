package edu.curso.estoque

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EstoqueApplication

fun main(args: Array<String>) {
	runApplication<EstoqueApplication>(*args)
}
