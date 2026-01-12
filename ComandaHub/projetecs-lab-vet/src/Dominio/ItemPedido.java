package Dominio;

public class ItemPedido extends AbsIdentificador {
    private int codigoPedido;
    private int quantidade;
    private Produto produto;
    public int getCodigoPedido() {
        return codigoPedido;
    }
    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItemPedido(){}
    public ItemPedido(int codigo, int codigoPedido, int quantidade) {
        super(codigo);
        this.codigoPedido = codigoPedido;
        this.quantidade = quantidade;
    }
}
