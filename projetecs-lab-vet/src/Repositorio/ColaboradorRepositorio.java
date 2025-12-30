package Repositorio;

import java.util.ArrayList;

import Dominio.Colaborador;
import FakeDB.ColaboradorFakeDB;

public class ColaboradorRepositorio extends AbsRepositorio<Colaborador> {

    public ColaboradorRepositorio(){
        this.baseDeDados = new ColaboradorFakeDB();
        this.dados = this.baseDeDados.getInstancia();
    }

    @Override
    public Colaborador create(Colaborador instancia) {
        Colaborador ultimo = this.dados.getLast();
        int chave = ultimo.getCodigo() + 1;
        instancia.setCodigo(chave);
        this.dados.add(instancia);
        return instancia;
    }


    @Override
    public Colaborador readOne(int codigo) {
        ArrayList<Colaborador> lista = this.dados;
        for(Colaborador colaborador : lista){
            if(colaborador.getCodigo() == codigo){
                return colaborador;
            } 
        }
        return null;
    }
    

    @Override
    public Colaborador update(Colaborador instancia) {
        Colaborador alterado = this.readOne(instancia.getCodigo());
        if(alterado != null){
            alterado.setCpf(instancia.getCpf());
            alterado.setEmail(instancia.getEmail());
            alterado.setMatricula(instancia.getNome());
            alterado.setNome(instancia.getNome());
            alterado.setSalario(instancia.getSalario());
        }
        return null;
    }

    @Override
    public Colaborador deleteById(int codigo) {
        Colaborador deletado = this.readOne(codigo);
            if(deletado != null){
                this.dados.remove(deletado);
                return deletado;
            }
        return null;
    }

    @Override
    public Colaborador deleteByInstance(Colaborador instancia) {
        return this.deleteById(instancia.getCodigo());
    }
        
}
