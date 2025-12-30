package Repositorio;

import java.util.ArrayList;

import Dominio.Animal;
import FakeDB.AnimalFakeDB;

public class AnimalRepositorio extends AbsRepositorio<Animal> {

    
    public AnimalRepositorio(){
        this.baseDeDados = new AnimalFakeDB();
        this.dados = this.baseDeDados.getInstancia();
    }

    @Override
    public Animal create(Animal instancia) {
        Animal ultimo = this.dados.getLast();
        int chave = ultimo.getCodigo() + 1;
        instancia.setCodigo(chave);
        this.dados.add(instancia);

        return instancia;
    }

    @Override
    public Animal readOne(int codigo) {
        ArrayList<Animal> lista = this.dados;
        for(Animal animal : lista){
            if(animal.getCodigo() == codigo){
                return animal;
            }
        }
        return null;
    }
    
    @Override
    public Animal update(Animal instancia) {
        Animal alterado = this.readOne(instancia.getCodigo());
        if (alterado != null){
            alterado.setNome(instancia.getNome());
            alterado.setEspecie(instancia.getEspecie());
            alterado.setRaca(instancia.getRaca());
            alterado.setDtNascimento(instancia.getDtNascimento());
        }
        return null;
    }

    @Override
    public Animal deleteById(int codigo) {
        Animal deletado = this.readOne(codigo);
        if(deletado != null){
            this.dados.remove(deletado);
            return deletado;
        }
        return null;
    }

    @Override
    public Animal deleteByInstance(Animal instancia) {
        return this.deleteById(instancia.getCodigo());
    }
 

    
}
