package edu.curso;
import java.io.Serializable;
import java.time.LocalDate;
public class Contato implements Serializable {
    String nome = "";
    String telefone = "";
    transient int idade;
    LocalDate nascimento = LocalDate.now();
}