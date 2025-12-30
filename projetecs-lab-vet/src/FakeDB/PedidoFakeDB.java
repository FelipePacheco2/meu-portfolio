package FakeDB;

import java.time.LocalDate;
import java.util.ArrayList;

import Dominio.ItemPedido;
import Dominio.Pedido;
import Dominio.Produto;

public class PedidoFakeDB extends AbsFakeDB<Pedido> {

    @Override
    protected void AutoPreencher() {
        if(this.instancia == null){
            this.instancia = new ArrayList<>();
        }

        this.instancia.add(criarPedido(1, 5001, LocalDate.now()));
        this.instancia.add(criarPedido(2, 5002, LocalDate.now()));
        this.instancia.add(criarPedido(3, 5003, LocalDate.now()));
    }
    
    private Pedido criarPedido(int codigo, int numero, LocalDate data){
        Pedido pedido = new Pedido(codigo, numero, data);
        ArrayList<ItemPedido> itens = new ArrayList<>();
        itens.add(this.criarItemPedido(1, 22202, 3));
        itens.add(this.criarItemPedido(2, 22202, 3));
        itens.add(this.criarItemPedido(3, 22202, 3));
        pedido.setItensPedido(itens);
        return pedido;
        
    }
    
    private ItemPedido criarItemPedido(int codigo, int codigoPedido, int quantidade){
        ItemPedido itempedido = new ItemPedido(codigo, codigoPedido, quantidade);
        itempedido.setProduto(this.criarProduto(1, "maça", 2, 5f));
        itempedido.setProduto(this.criarProduto(2, "pera", 2, 5f));
        itempedido.setProduto(this.criarProduto(3, "macarao", 2, 5f));
        return itempedido;
    }

    private Produto criarProduto(int codigo, String descricao, float precoCusto, float precoVenda){
        return new Produto(codigo, descricao, precoCusto, precoVenda);
    }

    public PedidoFakeDB(){
        super();
    }
}
