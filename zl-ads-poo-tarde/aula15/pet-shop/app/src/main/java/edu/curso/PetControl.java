package edu.curso;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PetControl {
    private ObservableList<Pet> lista = FXCollections.observableArrayList();

    private Connection con;

    StringProperty nome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    private PetDAO dao = new PetDAOImpl();

    public PetControl() { 
        
    }

    public void limparCampos() { 
        tipo.set("");
        nome.set("");
        nascimento.set(LocalDate.now());
    }

    public void salvar() { 
        Pet p = toEntity();
        lista.add( p );
        dao.cadastrar(p);
    }

    public void carregar() { 
        try { 
            String sql = "SELECT * FROM pet";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            lista.clear();
            while (rs.next()) { 
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                LocalDate nascimento = rs.getDate("nascimento").toLocalDate();
                Pet p = new Pet();
                p.setNome( nome );
                p.setTipo( tipo );
                p.setNascimento( nascimento );
                lista.add( p );
            }
            System.out.println("Comando executado com sucesso");   
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }
 
    public void pesquisar() { 
        for ( Pet p : lista ) { 
            if (p.getNome().contains( nome.get() )) { 
                toBoundary( p );
            }
        }
    }

    public Pet toEntity() { 
        Pet p = new Pet();
        p.setTipo( tipo.get() );
        p.setNome( nome.get() );
        p.setNascimento( nascimento.get() );
        return p;
    }

    public void toBoundary(Pet p) { 
        if (p != null) { 
            tipo.set(p.getTipo());
            nome.set(p.getNome());
            nascimento.set( p.getNascimento() );
        }
    }

    public ObservableList<Pet> getLista() { 
        return lista;
    }
    
}
