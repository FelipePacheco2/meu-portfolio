package Servico;

import java.util.ArrayList;
import Dominio.Pedido;
import Repositorio.PedidoRepositorio;

public class PedidoServico extends AbsServico<Pedido> implements Iservico<Pedido> {

    public PedidoServico(){
        this.repositorio = new PedidoRepositorio();
    }

    @Override
    public Pedido add(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Pedido> broser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido delete(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido deleteByInstancia(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido edit(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido read(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
