package edu.curso;

import java.util.List;

public interface PetDAO {
    public void cadastrar(Pet p);
    public List<Pet> consultarPorNome( String nome );
}
