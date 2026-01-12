package Repositorio;

import java.util.ArrayList;

import Dominio.Fornecedor;
import FakeDB.FornecedorFakeDB;

public class FornecedorRepositorio extends AbsRepositorio<Fornecedor> {

    public FornecedorRepositorio(){
        this.baseDeDados = new FornecedorFakeDB();
        this.dados = this.baseDeDados.getInstancia();

    }

    @Override
    public Fornecedor create(Fornecedor instancia) {
        Fornecedor ultimo = this.dados.getLast();
        int chave = ultimo.getCodigo() + 1;
        instancia.setCodigo(chave);
        this.dados.add(instancia);
        return instancia;
    }

       @Override
    public Fornecedor readOne(int codigo) {
        ArrayList<Fornecedor> lista = this.dados;
        for(Fornecedor fornecedor : lista){
            if(fornecedor.getCodigo() == codigo ){
                return fornecedor;
            }
        }
        return null;
    }

    @Override
    public Fornecedor update(Fornecedor instancia) {
        Fornecedor alterado = this.readOne(instancia.getCodigo());
        if(alterado != null){
            alterado.setCodigo(instancia.getCodigo());
            alterado.setCnpj(instancia.getCnpj());
            alterado.setContatoEmail(instancia.getContatoEmail());
            alterado.setContatoTel(instancia.getContatoTel());
            alterado.setNomeFantasia(instancia.getNomeFantasia());
            alterado.setRazaoSocial(instancia.getRazaoSocial());
        }
        return null;
    }

    @Override
    public Fornecedor deleteById(int codigo) {
        Fornecedor deletado = this.readOne(codigo);
        if(deletado != null){
            this.dados.remove(deletado);
            return deletado;
        }
        return null;
    }

    @Override
    public Fornecedor deleteByInstance(Fornecedor instancia) {
        return this.deleteById(instancia.getCodigo());
    }

    
}
