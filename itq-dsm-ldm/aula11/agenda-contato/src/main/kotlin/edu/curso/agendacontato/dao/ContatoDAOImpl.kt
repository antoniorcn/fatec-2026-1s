package edu.curso.agendacontato.dao

import edu.curso.agendacontato.model.Contato
import java.sql.DriverManager

class ContatoDAOImpl : ContatoDAO {

    var connection : java.sql.Connection? = null

    init {
        Class.forName("org.mariadb.jdbc.Drvier")
        connection = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/itq-estoque",
            "root",
            "123456"
        )
    }

    override fun salvar( contato : Contato ) {
        val sql = """
            |INSERT INTO contato(nome, telefone, email)
            |VALUES (?, ?, ?)
        """.trimMargin()
        val stm = connection?.prepareStatement(sql )
        stm?.setString(1, contato.nome)
        stm?.setString(2, contato.telefone)
        stm?.setString(3, contato.email)
        stm?.executeUpdate()
    }

    override fun listar() : List<Contato> {
        return listOf()
    }

}