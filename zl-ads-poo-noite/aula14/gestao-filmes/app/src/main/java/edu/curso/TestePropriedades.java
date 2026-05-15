package edu.curso;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestePropriedades {
    
    public static void main(String[] args) {
        
        StringProperty texto = new SimpleStringProperty("");


        // Função para assinar a propriedade texto
        texto.addListener( 
           ( obj, antigo, novo ) -> {
             System.out.printf("Mudou de: %s para: %s\n", antigo, novo);
           }  
        );


        texto.set("outro texto");
        texto.set("troquei novamente");
        texto.set("mais uma troca");
        System.out.println("Valor atual: "+ texto.get());


    }
}
