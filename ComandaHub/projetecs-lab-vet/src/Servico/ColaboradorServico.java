package Servico;

import java.util.ArrayList;

import Dominio.Colaborador;
import Repositorio.ColaboradorRepositorio;

public class ColaboradorServico extends AbsServico<Colaborador> implements Iservico<Colaborador> {
    public ColaboradorServico(){
        this.repositorio = new ColaboradorRepositorio();
    }

    @Override
    public Colaborador add(Colaborador instancia) {
        return this.repositorio.create(instancia);
    }

    @Override
    public ArrayList<Colaborador> broser() {
        return this.repositorio.readAll();
    }

    @Override
    public Colaborador delete(int codigo) {
        return this.repositorio.deleteById(codigo);
    }

    @Override
    public Colaborador deleteByInstancia(Colaborador instancia) {
        return this.repositorio.deleteByInstance(instancia);
    }

    @Override
    public Colaborador edit(Colaborador instancia) {
        return this.repositorio.update(instancia);
    }

    @Override
    public Colaborador read(int codigo) {
        return this.repositorio.readOne(codigo);
    }

    
}
