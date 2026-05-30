package edu.curso;

import java.util.List;

public interface FilmeDAO {
    void cadastrar(Filme f);
    void apagar(Filme f);
    void atualizar(long id, Filme f);
    List<Filme> pesquisarPorTitulo(String titulo);
}
