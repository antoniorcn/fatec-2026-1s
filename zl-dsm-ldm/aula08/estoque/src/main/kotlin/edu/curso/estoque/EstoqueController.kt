package edu.curso.estoque

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime

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

    @GetMapping("/dados")
    fun mostrarDados() : ModelAndView {

        val p1 = Pessoa(0, "Joao Silva", "(11) 1111-1111", "joao@teste.com")

        val mv = ModelAndView("dados")
        mv.addObject("projeto", "Teste Spring Boot com Koltin")
        mv.addObject("datetime", LocalDateTime.now().toString())
        mv.addObject("pessoa", p1)

        return mv
    }

}