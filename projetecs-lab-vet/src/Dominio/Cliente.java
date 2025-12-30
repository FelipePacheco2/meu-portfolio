package Dominio;

public class Cliente extends AbsPessoaFisica {
    private String numeroCartao;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Cliente(){
        super();
    }

    public Cliente(int codigo, String nome, String cpf, String email, String numeroCartao){
            this.codigo = codigo;
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.numeroCartao = numeroCartao;
    }
    
}
