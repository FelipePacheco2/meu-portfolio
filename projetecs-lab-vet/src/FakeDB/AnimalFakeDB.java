package FakeDB;

import java.util.ArrayList;
import java.time.LocalDate;
import Dominio.Animal;

public class AnimalFakeDB extends AbsFakeDB<Animal> {

    @Override
    protected void AutoPreencher(){
        if(this.instancia == null){
            this.instancia = new ArrayList<>();
        }

        this.instancia.add(new Animal(6, "bilu", "cão", "Beagle", LocalDate.of(2020, 01, 19) ));
        this.instancia.add(new Animal(7, "Skay", "ave", "Periquito", LocalDate.of(2019, 11, 02)));
    } 

    public AnimalFakeDB(){
        super();
    }

}
