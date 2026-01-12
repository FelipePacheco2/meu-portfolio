package Dominio;

import java.time.LocalDate;

public class Animal extends AbsIdentificador{

    private String nome;
    private String especie;
    private String raca;
    private LocalDate dtNascimento;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public LocalDate getDtNascimento() {
        return dtNascimento;
    }
    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Animal(){
        super();
    }

    public Animal(int codigo, String nome, String especie, String raca, LocalDate dtNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dtNascimento = dtNascimento;
    }
}