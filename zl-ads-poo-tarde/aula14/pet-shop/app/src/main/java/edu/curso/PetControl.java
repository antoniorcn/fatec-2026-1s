package edu.curso;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/agenda?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 

    private ObservableList<Pet> lista = FXCollections.observableArrayList();

    private Connection con;

    StringProperty nome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    // ChangeListener<String> observador = new ChangeListener<>() {
    //     public void changed(ObservableValue<? extends String> obj, String antigo, String novo) { 
    //         System.out.printf("Objeto alterado de %s para %s\n", antigo, novo);            
    //     } 
    // };

    public PetControl() { 
        System.out.println("Pet Control criado - com database");
         
        try {        
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");
            con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
            System.out.println("Conexao foi feita com sucesso");
            carregar();
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar a classe");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    public void limparCampos() { 
        tipo.set("");
        nome.set("");
        nascimento.set(LocalDate.now());
    }

    public void salvar() { 
        Pet p = toEntity();
        lista.add( p );

        try { 
            String sql = "INSERT INTO pet (nome, tipo, nascimento) VALUES " +
            "(?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, p.getNome());
            stm.setString(2, p.getTipo());
            stm.setDate(3, java.sql.Date.valueOf(p.getNascimento()));
            stm.executeUpdate();
            System.out.println("Comando executado com sucesso"); 
            carregar();  
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
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
