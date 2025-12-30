package Servico;

import java.util.ArrayList;

import Dominio.Animal;
import Repositorio.AnimalRepositorio;

public class AnimalServico extends AbsServico<Animal> implements Iservico<Animal> {

    public AnimalServico(){
        this.repositorio = new AnimalRepositorio();
    }

    @Override
    public Animal add(Animal instancia) {
        return this.repositorio.create(instancia);
    }

    @Override
    public ArrayList<Animal> broser() {
        return this.repositorio.readAll();
    }

    @Override
    public Animal delete(int codigo) {
        return this.repositorio.deleteById(codigo);
    }

    @Override
    public Animal deleteByInstancia(Animal instancia) {
        return this.repositorio.deleteByInstance(instancia);
    }

    @Override
    public Animal edit(Animal instancia) {
        return this.repositorio.update(instancia);
    }

    @Override
    public Animal read(int codigo) {
        return this.repositorio.readOne(codigo);
    }

    
}
