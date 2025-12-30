package Servico;

import java.util.ArrayList;

import Dominio.Fornecedor;
import Repositorio.FornecedorRepositorio;

public class FornecedorServico extends AbsServico<Fornecedor> implements Iservico<Fornecedor> {
    public FornecedorServico(){
        this.repositorio = new FornecedorRepositorio();
    }

    @Override
    public Fornecedor add(Fornecedor instancia) {
        return this.repositorio.create(instancia);
    }

    @Override
    public ArrayList<Fornecedor> broser() {
        return this.repositorio.readAll();
    }

    @Override
    public Fornecedor delete(int codigo) {
        return this.repositorio.deleteById(codigo);
    }

    @Override
    public Fornecedor deleteByInstancia(Fornecedor instancia) {
        return this.repositorio.deleteByInstance(instancia);
    }

    @Override
    public Fornecedor edit(Fornecedor instancia) {
        return this.repositorio.update(instancia);
    }

    @Override
    public Fornecedor read(int codigo) {
        return this.repositorio.readOne(codigo);
    }

    
}
