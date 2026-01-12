package FakeDB;

import java.util.ArrayList;

import Dominio.Colaborador;

public class ColaboradorFakeDB extends AbsFakeDB<Colaborador> {

    @Override
    protected void AutoPreencher() {
        if(this.instancia == null){
            this.instancia = new ArrayList<>();
        }
        this.instancia.add(new Colaborador(15, "ronaldo", "111", "111.111.111-11", 1200, "ronald@gmai"));
        this.instancia.add(new Colaborador(13, "rayssa", "222", "222.111.222-11", 1500, "rayssa@gmai"));
        
    }

    public ColaboradorFakeDB(){
        super();
    }

    
    
}
