package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/agenda?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    private Connection con;

    public PetDAOImpl() { 
        System.out.println("Pet Control criado - com database");
        try {        
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");
            con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
            System.out.println("Conexao foi feita com sucesso");
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar a classe");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public void cadastrar(Pet p) {
        try { 
            String sql = "INSERT INTO pet (nome, tipo, nascimento) VALUES " +
            "(?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, p.getNome());
            stm.setString(2, p.getTipo());
            stm.setDate(3, java.sql.Date.valueOf(p.getNascimento()));
            stm.executeUpdate();
            System.out.println("Comando executado com sucesso"); 
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> consultarPorNome(String nome) {
        
    }
    
}
