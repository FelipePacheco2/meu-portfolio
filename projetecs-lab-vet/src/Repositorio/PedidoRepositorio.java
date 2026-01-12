package Repositorio;

import Dominio.Pedido;
import FakeDB.PedidoFakeDB;

public class PedidoRepositorio extends AbsRepositorio<Pedido>{

    public PedidoRepositorio(){
        this.baseDeDados = new PedidoFakeDB();
        this.dados = this.baseDeDados.getInstancia();

    }

    @Override
    public Pedido create(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido deleteById(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido deleteByInstance(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido readOne(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido update(Pedido instancia) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
