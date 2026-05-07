package edu.curso.estoque.repository

import edu.curso.estoque.model.Categoria
import java.sql.Connection
import java.sql.DriverManager

class CategoriaRepositoryOldFashion {
    val JDBC_CLASS = "org.mariadb.jdbc.Driver"
    val JDBC_URI = "jdbc:mariadb://localhost:3306/estoque_zl"
    val JDBC_USER = "root"
    val JDBC_PASS = "123456"

    var connection : Connection? = null
    init  {
        if (connection == null) {
            try {
                Class.forName(JDBC_CLASS)
                connection = DriverManager.getConnection(JDBC_URI, JDBC_USER, JDBC_PASS)
            } catch ( err : Exception ) {
                println("Erro ao carregar driver JDBC: ${err.message}")
            }
        }
    }

    fun adicionar( categoria : Categoria) {
        val sql = "INSERT INTO categoria (nome, descricao) VALUES (? ,?)"
        try {
            val stmt = connection?.prepareStatement(sql)
            if (stmt != null) {
                stmt.setString(1, categoria.nome)
                stmt.setString(2 , categoria.descricao)
                stmt.executeUpdate()
            }
        } catch (err : Exception) {
            println("Erro ao gerar a query driver JDBC: ${err.message}")
        }
    }
}