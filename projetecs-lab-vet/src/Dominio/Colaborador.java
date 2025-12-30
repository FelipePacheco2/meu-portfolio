package Dominio;

public class Colaborador extends AbsPessoaFisica{
    private Double salario;
    private String matricula;

    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Colaborador(){
        super();
    }

    public Colaborador(int codigo, String nome, String matricula, String cpf, double salario, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.codigo = codigo;
        this.salario = salario;
        this.matricula = matricula;
    }
    
}
