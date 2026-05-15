package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmeControl {
    private static final String DB_URI = 
        "jdbc:mariadb://localhost:3306/hollywood?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    
    private ObservableList<Filme> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty titulo = new SimpleStringProperty("");
    private StringProperty genero = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> lancamento = new SimpleObjectProperty<>(LocalDate.now());

    private Connection con;

    public FilmeControl() { 
        try { 
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Carregado...");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado no banco de dados...");
            carregar();
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar o Driver");
            e.printStackTrace();
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    public void fromEntity( Filme f ) {
        if (f != null) {
            id.set( f.getId() );
            titulo.set( f.getTitulo() );
            genero.set( f.getGenero() );
            lancamento.set(f.getLancamento());
        }
    }

    public Filme toEntity() { 
        Filme f = new Filme();
        f.setId( id.get() );
        f.setTitulo( titulo.get() );
        f.setGenero( genero.get() );
        f.setLancamento( lancamento.get() );
        return f;
    }

    public void salvar() {
        Filme f = toEntity();
        System.out.println("ID do Filme ==> " + f.getId());
        // lista.add( f );
        try { 
            if (f.getId() == 0) {
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
            } else { 
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
            }
            carregar();
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    public void carregar() { 
        try { 
            String sql = "SELECT * FROM filme";

            PreparedStatement stm = con.prepareStatement( sql );
            ResultSet rs = stm.executeQuery();
            System.out.println("Filmes selecionados com sucesso");
            lista.clear();
            while (rs.next()) { 
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                LocalDate lancamento = rs.getDate("lancamento").toLocalDate();
                Filme f = new Filme();
                f.setId(id);
                f.setTitulo(titulo);
                f.setGenero(genero);
                f.setLancamento(lancamento);

                lista.add(f);
            }
        } catch (SQLException e) { 
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    public Filme pesquisar() {
        for ( Filme f : lista ) { 
            if (f.getTitulo().contains( titulo.get() )) { 
               fromEntity( f ); 
            }
        }
        return null;
    }

    public String getTitulo() { 
        return titulo.get();
    }

    public StringProperty tituloProperty() { 
        return titulo;
    }

    public StringProperty generoProperty() { 
        return genero;
    }

    public ObjectProperty<LocalDate> lancamentoProperty() { 
        return lancamento;
    }

    public ObservableList<Filme> getLista() { 
        return lista;
    }

}
