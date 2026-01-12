package Dominio;

public class Veterinario extends AbsPessoaFisica{
    private String rg;
    private String regCRMV; 

    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getRegCRMV() {
        return regCRMV;
    }
    public void setRegCRMV(String regCRMV) {
        this.regCRMV = regCRMV;
    }

    public Veterinario(){super();}
    
    public Veterinario(int codigo, String nome, String rg, String cpf, String email, String regCRMV){
        this.codigo = codigo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.regCRMV = regCRMV; 
    }

}
