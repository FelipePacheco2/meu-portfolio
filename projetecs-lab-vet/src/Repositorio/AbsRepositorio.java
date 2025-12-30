package Repositorio;

import java.util.ArrayList;

import FakeDB.AbsFakeDB;

public abstract class AbsRepositorio<TDominio> {

    protected AbsFakeDB<TDominio> baseDeDados;

    protected ArrayList<TDominio> dados;        

    public abstract TDominio create(TDominio instancia);

    public abstract TDominio readOne(int codigo);

    public ArrayList<TDominio> readAll(){
        return this.dados;
    }

    public abstract TDominio update(TDominio instancia);

    public abstract TDominio deleteById(int codigo);

    public abstract TDominio deleteByInstance(TDominio instancia);

    public AbsRepositorio(){
    }
}
