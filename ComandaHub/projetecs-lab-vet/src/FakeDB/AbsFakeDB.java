package FakeDB;

import java.util.ArrayList;

public abstract class AbsFakeDB<TDominio> {

    protected ArrayList<TDominio> instancia;

    public ArrayList<TDominio> getInstancia(){
        if (this.instancia != null){
            return this.instancia;
        }
        else{
            this.instancia = new ArrayList<>();
            return this.instancia;
        }
    }

    protected abstract void AutoPreencher();

    public AbsFakeDB(){
        this.AutoPreencher();
    }
}