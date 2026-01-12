package Servico;

import java.util.ArrayList;

import Dominio.Cliente;
import Repositorio.ClienteRepositorio;

public class ClienteServico extends AbsServico<Cliente> implements Iservico<Cliente> {
    public ClienteServico(){
        this.repositorio = new ClienteRepositorio();
    }

    @Override
    public Cliente add(Cliente instancia) {
        return this.repositorio.create(instancia);
    }

    @Override
    public ArrayList<Cliente> broser() {
        return this.repositorio.readAll();
    }

    @Override
    public Cliente delete(int codigo) {
        return this.repositorio.deleteById(codigo);
    }

    @Override
    public Cliente deleteByInstancia(Cliente instancia) {
        return this.repositorio.deleteByInstance(instancia);
    }

    @Override
    public Cliente edit(Cliente instancia) {
        return this.repositorio.update(instancia);
    }

    @Override
    public Cliente read(int codigo) {
        return this.repositorio.readOne(codigo);
    }

    
}
