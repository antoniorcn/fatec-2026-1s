package edu.curso.estoque

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class EstoqueController {

    @GetMapping("/")
    fun olaMundo() : ResponseEntity<String> {
        return ResponseEntity.ok("Ola mundo")
    }

    @GetMapping("/pagina")
    fun pagina1() : String {
        return "pagina1"
    }

}