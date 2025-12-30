package Dominio;

public abstract class AbsPessoaFisica extends AbsIdentificador {
    protected String nome;
    protected String cpf;
    protected String email;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public AbsPessoaFisica(){
        super();
    }
}
