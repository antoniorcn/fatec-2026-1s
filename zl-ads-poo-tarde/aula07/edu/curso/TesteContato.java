package edu.curso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;

public class TesteContato { 
    public static void main(String args[]) { 
        Contato c1 = new Contato("Joao", "1111", "joao@teste.com");

        File f = new File("C:\\temp\\contato.data");

        try ( OutputStream out = new FileOutputStream( f ) ) { 
            ObjectOutputStream objOut = new ObjectOutputStream( out );
            objOut.writeObject( c1 );
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}