package Dominio;

public class Fornecedor extends AbsPessoaJuridica{
    private String contatoTel;
    private String contatoEmail;

    public String getContatoTel() {
        return contatoTel;
    }
    public void setContatoTel(String contatoTel) {
        this.contatoTel = contatoTel;
    }
    public String getContatoEmail() {
        return contatoEmail;
    }
    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public Fornecedor(){
        super();
    }

    public Fornecedor(int codigo,String razaoSocial, String nomeFantasia, String cnpj, String contatoTel, String contatoEmail){
        this.codigo = codigo; 
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.contatoTel = contatoTel;
        this.contatoEmail = contatoEmail;
    }
}
