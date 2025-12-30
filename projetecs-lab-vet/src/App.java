import java.util.ArrayList;

import Dominio.Animal;
import Dominio.Cliente;
import Dominio.Colaborador;
import Dominio.Fornecedor;
import Dominio.ItemPedido;
import Dominio.Pedido;
import Dominio.Veterinario;
import Repositorio.AnimalRepositorio;
import Repositorio.ClienteRepositorio;
import Repositorio.ColaboradorRepositorio;
import Repositorio.FornecedorRepositorio;
import Repositorio.PedidoRepositorio;
import Repositorio.VeterinarioRepositorio;

public class App {
    public static void main(String[] args) throws Exception {

        int totalqts = 0; 
        float totalValor = 0;
        float totalCusto = 0;

        ClienteRepositorio RepClient = new ClienteRepositorio();
        ArrayList<Cliente> cliente = RepClient.readAll();
        System.out.println("Clientes");
        for(Cliente clientes : cliente){
            System.out.println("Codigo: " + clientes.getCodigo());
            System.out.println("Nome: " + clientes.getNome());
            System.out.println("CPF: " + clientes.getCpf());
        }

        System.out.println("");

        ColaboradorRepositorio RepColab = new ColaboradorRepositorio();
        ArrayList<Colaborador> colaborador = RepColab.readAll();
        System.out.println("Colaboradores");
        for(Colaborador colaboradors : colaborador){
            System.out.println("Matricula: " + colaboradors.getMatricula());
            System.out.println("Nome: " + colaboradors.getNome());
            System.out.println("Salario: " + colaboradors.getSalario());
        }
        
        System.out.println("");

        AnimalRepositorio RepAnimal = new AnimalRepositorio();
        ArrayList<Animal> animal = RepAnimal.readAll();
        for(Animal animals : animal){
            System.out.println("Codigo: " + animals.getCodigo());
            System.out.println("Nome: " + animals.getNome());
            System.out.println("Raça: " + animals.getRaca());
            System.out.println("Especie: " + animals.getEspecie());
            System.out.println("DataNascimento: " + animals.getDtNascimento());
        }

        System.out.println("");

        FornecedorRepositorio RepForn = new FornecedorRepositorio();
        ArrayList<Fornecedor> fornecedor = RepForn.readAll();
        System.out.println("Fornecedores");
        for(Fornecedor fornecedors : fornecedor){
            System.out.println("Codigo: " + fornecedors.getCodigo());
            System.out.println("RazaoSocial: "+ fornecedors.getRazaoSocial());
            System.out.println("CNPJ: "+ fornecedors.getCnpj());
        }

        System.out.println("");

        VeterinarioRepositorio RepVet = new VeterinarioRepositorio();
        ArrayList<Veterinario> veterinario = RepVet.readAll();
        System.out.println("veterinarios");
        for(Veterinario veterinarios : veterinario){
            System.out.println("Codigo: " + veterinarios.getCodigo());
            System.out.println("Nome:" + veterinarios.getNome());
            System.out.println("CPF: " + veterinarios.getCpf());
            System.out.println("RG: " + veterinarios.getRg());
            System.out.println("CRMV " + veterinarios.getRegCRMV());
        }

        System.out.println("");

        PedidoRepositorio RepPedido = new PedidoRepositorio();
        ArrayList<Pedido> pedido = RepPedido.readAll();
        for(Pedido pedidos : pedido){
            System.out.println("------PEDIDO------");
            System.out.println("Codigo: " + pedidos.getCodigo());
            System.out.println("Numero Pedido: " + pedidos.getNumero());
            System.out.println("Data Pedido: "+ pedidos.getData());
            System.out.println("\t------ITENS------");
            for(ItemPedido item : pedidos.getItensPedido()){
                System.out.println("\tCodigo do item: " + item.getCodigo());
                System.out.println("\tPreço Unitário: " + item.getProduto().getPrecoVenda());
                System.out.println("\tQuantidade: "+ item.getQuantidade());
                totalqts += item.getQuantidade();
                totalValor += item.getProduto().getPrecoVenda();
                totalCusto += item.getProduto().getPrecoCusto();
            }
        }

        System.out.println("------CAIXA------");
        System.err.println("total de vendas: " + totalqts);
        System.err.println("total Valor Bruto: R$" + totalValor);
        System.err.println("total Lucro: R$" + (totalValor - totalCusto));
        
    }
}
