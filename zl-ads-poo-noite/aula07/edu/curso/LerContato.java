package edu.curso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class LerContato {

    public static void main(String[] args) {
        System.out.println("Ler Contato do arquivo");
        File file = new File("C:\\temp\\contato.data");
        try (InputStream in = new FileInputStream( file )) {
            System.out.println("Lendo do arquivo...");
            ObjectInputStream objIn = new ObjectInputStream( in );
            Object obj = objIn.readObject();
            Contato c2 = (Contato)obj;
            System.out.println("Nome: " + c2.nome);
            System.out.println("Telefone: " + c2.telefone);
            System.out.println("Nascimento: " + c2.nascimento);
            System.out.println("Idade: " + c2.idade);
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }
    
}
