package Dominio;

public class Produto extends AbsIdentificador {
    private String descricao;
    private float precoCusto;
    private float precoVenda;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public float getPrecoCusto() {
        return precoCusto;
    }
    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }
    public float getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }
    public Produto() {
    }
    public Produto(int codigo, String descricao, float precoCusto, float precoVenda) {
        super(codigo);
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }
}
