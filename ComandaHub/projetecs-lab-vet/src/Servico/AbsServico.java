package Servico;

import Repositorio.AbsRepositorio;

public abstract class AbsServico<TDominio>{
    protected AbsRepositorio<TDominio> repositorio;
}
