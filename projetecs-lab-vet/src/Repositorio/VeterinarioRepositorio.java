package Repositorio;

import java.util.ArrayList;

import Dominio.Veterinario;
import FakeDB.VeterinarioFakeDB;

public class VeterinarioRepositorio extends AbsRepositorio<Veterinario>{
    
    public VeterinarioRepositorio(){
        this.baseDeDados = new VeterinarioFakeDB();
        this.dados = this.baseDeDados.getInstancia();
    }

    @Override
    public Veterinario create(Veterinario instancia) {
        Veterinario ultimo = this.dados.getLast();
        int chave = ultimo.getCodigo() + 1;
        instancia.setCodigo(chave);
        this.dados.add(instancia);
        return null;
    }

        @Override
    public Veterinario readOne(int codigo) {
        ArrayList<Veterinario> lista = this.dados;
        for (Veterinario veterinario : lista){
            if(veterinario.getCodigo() == codigo){
                return veterinario;
            }
        }
        return null; 
    }


    @Override
        public Veterinario update(Veterinario instancia) {
            Veterinario alterado = this.readOne(instancia.getCodigo());
            if(alterado != null){
                alterado.setCodigo(instancia.getCodigo());
                alterado.setNome(instancia.getNome());
                alterado.setCpf(instancia.getCpf());
                alterado.setEmail(instancia.getEmail());
                alterado.setRegCRMV(instancia.getRegCRMV());
                alterado.setRg(instancia.getRg());
            }        
            return null;
        }

    @Override
    public Veterinario deleteById(int codigo) {
        Veterinario deletado = this.readOne(codigo);
        if(deletado != null){
            this.dados.remove(deletado);
            return deletado;
        }
        return null;
    }

    @Override
    public Veterinario deleteByInstance(Veterinario instancia) {
        return this.deleteById(instancia.getCodigo());
    }
    
}
