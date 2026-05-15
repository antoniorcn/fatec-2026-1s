package edu.curso;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class TesteDB { 
    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/agenda?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 

    public static void main( String args[] ) { 
        System.out.println("Teste de Banco de Dados");
        try {        
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");
            Connection con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
            System.out.println("Conexao foi feita com sucesso");

            String sql = "INSERT INTO contato (nome, telefone, email) VALUES " +
            "('Joao Silva', '(11) 1111-1111', 'joao@teste.com')";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            System.out.println("Comando executado com sucesso");
            con.close();
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar a classe");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }
}