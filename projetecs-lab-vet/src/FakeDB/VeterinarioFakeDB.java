package FakeDB;

import java.util.ArrayList;

import Dominio.Veterinario;

public class VeterinarioFakeDB extends AbsFakeDB<Veterinario>{

    @Override
    protected void AutoPreencher() {
        if(this.instancia == null){
            this.instancia = new ArrayList<>();
        }

        this.instancia.add(new Veterinario(102, "Lara", "273283", "000.111.222-23", "lara@gmail.com", "88888"));        
        this.instancia.add(new Veterinario(102, "Lara", "273283", "000.111.222-23", "lara@gmail.com", "88888"));

    }
    
    public VeterinarioFakeDB(){super();}
    
}
