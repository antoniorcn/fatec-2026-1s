package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAOImplementation implements FilmeDAO {
    private static final String DB_URI = 
        "jdbc:mariadb://localhost:3306/hollywood?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 

    private Connection con;

    public FilmeDAOImplementation() { 
        try { 
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Carregado...");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado no banco de dados...");
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar o Driver");
            e.printStackTrace();
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public void cadastrar(Filme f) {
        try { 
            String sql = "INSERT INTO filme (titulo, genero, lancamento) "+ 
            "VALUES (?, ?, ?)";

            PreparedStatement stm = con.prepareStatement( sql );
            stm.setString(1, f.getTitulo());
            stm.setString(2, f.getGenero());
            stm.setDate(3, 
                java.sql.Date.valueOf( f.getLancamento()) 
            );
            stm.executeUpdate();
            System.out.println("Filme inserido com sucesso");
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Filme f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apagar'");
    }

    @Override
    public void atualizar(long id, Filme f) {
        try { 
            String sql = "UPDATE filme SET titulo=?, genero=?, lancamento=? "+ 
                    "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement( sql );
            stm.setString(1, f.getTitulo());
            stm.setString(2, f.getGenero());
            stm.setDate(3, 
                java.sql.Date.valueOf( f.getLancamento()) 
            );
            stm.setLong(4, f.getId());
            stm.executeUpdate();
            System.out.println("Filme atualizado com sucesso");
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public List<Filme> pesquisarPorTitulo(String titulo) {
        List<Filme> lista = new ArrayList<>();
        try { 
            String sql = "SELECT * FROM filme WHERE titulo LIKE ?";

            PreparedStatement stm = con.prepareStatement( sql );
            stm.setString(1, "%" + titulo + "%");
            ResultSet rs = stm.executeQuery();
            System.out.println("Filmes selecionados com sucesso");
            while (rs.next()) { 
                long id = rs.getLong("id");
                String tituloFilme = rs.getString("titulo");
                String genero = rs.getString("genero");
                LocalDate lancamento = rs.getDate("lancamento").toLocalDate();
                Filme f = new Filme();
                f.setId(id);
                f.setTitulo(tituloFilme);
                f.setGenero(genero);
                f.setLancamento(lancamento);

                lista.add( f ); 
            }
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
        return lista;
    }
    
}
