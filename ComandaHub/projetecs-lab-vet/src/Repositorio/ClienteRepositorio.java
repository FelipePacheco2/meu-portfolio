package Repositorio;

import java.util.ArrayList;

import Dominio.Cliente;
import FakeDB.ClienteFakeDB;

public class ClienteRepositorio extends AbsRepositorio<Cliente>{

    public ClienteRepositorio(){
        this.baseDeDados = new ClienteFakeDB();
        this.dados = this.baseDeDados.getInstancia();
    }

    @Override
    public Cliente create(Cliente instancia) {
        Cliente ultimo = this.dados.getLast();
        int chave = ultimo.getCodigo() + 1;
        instancia.setCodigo(chave);
        this.dados.add(instancia);
        return instancia;
    }

    @Override
    public Cliente readOne(int codigo) {
        ArrayList<Cliente> lista = this.dados;
        for (Cliente cliente : lista) {
            if(cliente.getCodigo() == codigo){
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente update(Cliente instancia) {
        Cliente alterado = this.readOne(instancia.getCodigo());
        if (alterado != null){
            alterado.setNome(instancia.getNome());
            alterado.setCpf(instancia.getCpf());
            alterado.setEmail(instancia.getEmail());
            alterado.setNumeroCartao(instancia.getNumeroCartao());
            return alterado;
        }
        return alterado;
    }

    @Override
    public Cliente deleteById(int codigo) {
        Cliente deletado = this.readOne(codigo);
        if (deletado != null){
            this.dados.remove(deletado);
            return deletado;
        }
        return deletado;
    }

    @Override
    public Cliente deleteByInstance(Cliente instancia) {
        return this.deleteById(instancia.getCodigo());
    }
    
}
