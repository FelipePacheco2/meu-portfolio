package FakeDB;

import java.util.ArrayList;

import Dominio.Fornecedor;

public class FornecedorFakeDB extends AbsFakeDB<Fornecedor>{

    @Override
    protected void AutoPreencher() {
        if(this.instancia == null){
            this.instancia = new ArrayList<>();
        }
        
        this.instancia.add(new Fornecedor(54, "Fornecedor de Ração para Gatos", "CAT-food", "20.182.807/0004-42", "(67 99123-4567)", "Cat-food@gmail.com"));
        this.instancia.add(new Fornecedor(55, "Fornecedor de Ração para Cachorros", "DOG-food", "50.761.450/0001-58", "(67 99864-9750)", "Dog-food@gmail.com"));
    }

    public FornecedorFakeDB(){
        super();
    }
        
}
