package edu.curso;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class TesteDB {
    private static final String DB_URI = 
        "jdbc:mariadb://localhost:3306/hollywood?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    
    public static void main(String[] args) {
        System.out.println("Teste de Banco de Dados");
        try { 
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Carregado...");
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado no banco de dados...");
            String sql = "INSERT INTO filme (titulo, genero, lancamento) VALUES " + 
            "('A Chegada', 'Ficção Científica', '2016-08-15')";

            Statement stm = con.createStatement();
            stm.executeUpdate( sql );
            System.out.println("Filme inserido com sucesso");

        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar o Driver");
            e.printStackTrace();
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }
}
