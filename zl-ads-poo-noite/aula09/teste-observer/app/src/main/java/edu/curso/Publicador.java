package edu.curso;

public interface Publicador {

    void adicionar(Assinante a);
    void remover(Assinante a);
    void publicar( String msg );  
    // for each para repassar a msg a 
    // todos os assinantes cadastrados
    
}
