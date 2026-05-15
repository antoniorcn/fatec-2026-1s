package edu.curso.agendacontato.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class HelloController {

    @GetMapping("/")
    fun indice() : String {
        return "hello"
    }

    @GetMapping("/help")
    fun contato() : ModelAndView {
        val mv = ModelAndView("contato")
        mv.addObject("contatoNome", "Joao Silva")
        mv.addObject("contatoTelefone", "(11) 1111-1111")
        mv.addObject("contatoEmail", "joao@teste.com")
        return mv
    }


    @GetMapping("/agenda")
    fun agenda() : ResponseEntity<String> {
        return ResponseEntity.ok().body("Ola mundo você acessou a agenda")
    }

}