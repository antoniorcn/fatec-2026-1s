package edu.curso;

import java.time.LocalDate;
import java.io.File;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class SalvarContato { 

    public static void main(String[] args) {
        System.out.println("Teste de Contato");
        Contato c1 = new Contato();
        c1.nome = "Joao Silva";
        c1.telefone = "(11) 1111-1111";
        c1.idade = 20;
        c1.nascimento = LocalDate.of(2004, 8, 30);

        File file = new File("C:\\temp\\contato.data");
        try (OutputStream out = new FileOutputStream( file )) {
            System.out.println("Gravando no arquivo...");
            ObjectOutputStream objOut = new ObjectOutputStream( out );

            objOut.writeObject( c1 ); 

        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}