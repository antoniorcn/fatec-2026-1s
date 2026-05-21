package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/zl_pet_tarde?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    private Connection con;

    public PetDAOImpl() { 
        System.out.println("Pet DAO criado - com database");
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
        List<Pet> lista = new ArrayList<>();
        try { 
            String sql = "SELECT * FROM pet WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
                Long id = rs.getLong("id");
                String petNome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                LocalDate nascimento = rs.getDate("nascimento").toLocalDate();
                Pet p = new Pet();
                p.setId(id);
                p.setNome( petNome );
                p.setTipo( tipo );
                p.setNascimento( nascimento );
                lista.add( p );
            }
            System.out.println("Comando executado com sucesso");   
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizar(long id, Pet p) {
        try { 
            String sql = "UPDATE pet SET nome = ?, tipo = ?, nascimento = ? WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, p.getNome());
            stm.setString(2, p.getTipo());
            stm.setDate(3, java.sql.Date.valueOf(p.getNascimento()));
            stm.setLong(4, id);
            stm.executeUpdate();
            System.out.println("Pet atualizado com sucesso"); 
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }
}
