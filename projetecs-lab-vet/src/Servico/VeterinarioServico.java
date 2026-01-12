package Servico;

import java.util.ArrayList;

import Dominio.Veterinario;
import Repositorio.VeterinarioRepositorio;

public class VeterinarioServico extends AbsServico<Veterinario> implements Iservico<Veterinario> {
    public VeterinarioServico(){
        this.repositorio = new VeterinarioRepositorio();
    }

    @Override
    public Veterinario add(Veterinario instancia) {
        return this.repositorio.create(instancia);
    }

    @Override
    public ArrayList<Veterinario> broser() {
        return this.repositorio.readAll();
    }

    @Override
    public Veterinario delete(int codigo) {
        return this.repositorio.deleteById(codigo);
    }

    @Override
    public Veterinario deleteByInstancia(Veterinario instancia) {
        return this.repositorio.deleteByInstance(instancia);
    }

    @Override
    public Veterinario edit(Veterinario instancia) {
        return this.repositorio.update(instancia);
    }

    @Override
    public Veterinario read(int codigo) {
        return this.repositorio.readOne(codigo);
    }

    
}
