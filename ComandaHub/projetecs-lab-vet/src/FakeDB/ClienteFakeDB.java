package FakeDB;

import java.util.ArrayList;

import Dominio.Cliente;

public class ClienteFakeDB extends AbsFakeDB<Cliente>{

    @Override
    protected void AutoPreencher() {
        if (this.instancia == null){
            this.instancia = new ArrayList<>();
        }
        this.instancia.add(new Cliente(1, "Luiz", "123", "luiz@uniderp", "123"));
        this.instancia.add(new Cliente(2, "Maria", "456", "maria@uniderp", "765"));
        this.instancia.add(new Cliente(3, "João", "789", "joao@uniderp", "143"));
        this.instancia.add(new Cliente(4, "José", "321", "jose@uniderp", "376"));
        this.instancia.add(new Cliente(5, "Paulo", "654", "paulo@uniderp", "872"));
    }

    public ClienteFakeDB(){
        super();
    }
}
