package edu.curso.agendacontato.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class FiltroA : OncePerRequestFilter() {

    override fun doFilterInternal(
        request : HttpServletRequest,
        response : HttpServletResponse,
        filterChain : FilterChain
    ) {
        println("Filtro A ${request.method}-${request.requestURI}")
        filterChain.doFilter(request, response)
    }

}
