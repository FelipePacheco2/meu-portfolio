package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido extends AbsIdentificador{
    private int numero;
    private LocalDate data;
    private ArrayList<ItemPedido> itensPedido;

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Pedido(){}
    public Pedido(int codigo, int numero, LocalDate data) {
        super(codigo);
        this.numero = numero;
        this.data = data;
    }

    
    
    
}
