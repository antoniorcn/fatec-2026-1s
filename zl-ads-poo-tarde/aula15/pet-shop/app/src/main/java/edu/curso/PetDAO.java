package edu.curso;

import java.util.List;

public interface PetDAO {
    void cadastrar(Pet p);
    List<Pet> consultarPorNome( String nome );
    void atualizar(long id, Pet p);
}
