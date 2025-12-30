package Servico;

import java.util.ArrayList;

public interface Iservico<TDominio> {
    ArrayList<TDominio> broser();
    TDominio read(int codigo);
    TDominio edit(TDominio instancia);
    TDominio add(TDominio instancia);
    TDominio delete(int codigo);
    TDominio deleteByInstancia(TDominio instancia);
     
}
    
