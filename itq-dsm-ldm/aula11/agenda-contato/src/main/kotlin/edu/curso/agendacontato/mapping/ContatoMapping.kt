package edu.curso.agendacontato.mapping

import edu.curso.agendacontato.dto.ContatoDto
import edu.curso.agendacontato.dto.ContatoDtoView
import edu.curso.agendacontato.model.Contato
import org.springframework.stereotype.Component

@Component
class ContatoMapping {
    fun toEntity( contatoDto : ContatoDto) : Contato{
        return Contato(
            id = 0,
            nome = contatoDto.nome,
            email = contatoDto.email,
            telefone = contatoDto.telefone
        )
    }

    fun toDto( contato : Contato ) : ContatoDto{
        return ContatoDto(nome = contato.nome,
            email = contato.email,
            telefone = contato.telefone)
    }

    fun toDtoView( contato : Contato ) : ContatoDtoView  {
        return ContatoDtoView(
            nome = contato.nome,
            email = contato.email
        )
    }
}