package com.br.felipe.Views;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.br.felipe.Controller.AccountController;
import com.br.felipe.Controller.CustomerController;
import com.br.felipe.Model.Account;
import com.br.felipe.Model.Customer;

public class AllViews {

    Console console = System.console();
    Scanner input;

    CustomerController adm;
    AccountController customer;

    public AllViews(){
        this.adm = new CustomerController();
        this.customer = new AccountController();
        this.input = new Scanner(System.in);
    }

    //capturas
    public Customer CreateBuilderCustumerid(int id){

        System.out.println("Digite o primeiro nome: ");
        String firstName = console.readLine();
        System.out.print(" ");
        System.out.println("Digite o segundo nome: ");
        String lastName = console.readLine();
        System.out.print(" ");
        System.out.println("Digite o cpf: ");
        String cpf = console.readLine();
        System.out.printf(" ");
        Customer customer = new Customer(id, firstName, lastName, cpf);
        return customer;
    }
    public Customer CreateBuilderCustumer(){
        
        System.out.print("➤ Digite o primeiro nome: ");
        String firstName = console.readLine();

        System.out.print("➤ Digite o segundo nome: ");
        String lastName = console.readLine();

        System.out.print("➤ Digite o cpf: ");
        String cpf = console.readLine();

        Customer customer = new Customer(firstName, lastName, cpf);
        return customer;
    }
    public int CaptureCustomerID(){

        int customer_id = 0;
        
        try {
            System.out.print("➤➤ Digite o ID: ");
            customer_id = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.err.println("➤➤ Invalido ➤➤");
            System.out.println("------------------------------");
            CaptureCustomerID();
        }
   
        return customer_id;
    }
    public Account CreateBuildAccount(int id){
        Account account = new Account(null, id);
        return account;
    }

    public BigDecimal Movement() {
        try {
            System.out.print("➤ Digite o valor: ");
            BigDecimal value = this.input.nextBigDecimal(); 
            this.input.nextLine(); 
            System.out.println("------------------------------");
            return value;
        } catch (Exception e) {
            System.out.println("➤➤ Erro: Valor inválido! ➤➤");
            System.out.println("------------------------------");
            this.input.nextLine();
            return BigDecimal.ZERO;
        }
    
}

    public void iniciaSistema(){
        int opcao;
        System.out.println("==============================");
        System.out.println("      SISTEMA BANCÁRIO        ");
        System.out.println("==============================");
        System.out.println("  [1] Acesso Cliente");
        System.out.println("  [2] Acesso Administrador");
        System.out.println("  [0] Sair");
        System.out.println("------------------------------");
        System.out.print("➤ Selecione seu perfil: ");

        opcao = Integer.parseInt(console.readLine());

        switch (opcao) {
            case 1 -> customer.CustomerMenu();
            case 2 -> adm.AdmMenu();
            default -> System.out.println("➤➤ Opção inválida! Tente novamente. ➤➤");
        }

    }
    //Imprimir
    public void DisplayADMMenu() {
        System.out.println("\n==============================");
        System.out.println("       PAINEL ADM - CRUD      ");
        System.out.println("==============================");
        System.out.println("  [1] Cadastrar Cliente");
        System.out.println("  [2] Listar Todos");
        System.out.println("  [3] Buscar por ID");
        System.out.println("  [4] Atualizar");
        System.out.println("  [5] Deletar");
        System.out.println("  [0] Sair");
        System.out.println("------------------------------");
        System.out.print("➤ Escolha uma opção: ");
    }
    
     public void DisplayCustomerMenu() {
        System.out.println("\n==============================");
        System.out.println("        ÁREA DO CLIENTE       ");
        System.out.println("==============================");
        System.out.println("  [1] Ver Perfil");
        System.out.println("  [2] Sacar (Saque)");
        System.out.println("  [3] Depositar");
        System.out.println("  [0] Sair");
        System.out.println("------------------------------");
        System.out.print("➤ Digite a operação desejada: ");
    }


    public void TableCustomer(Customer customer){
        if(customer != null){
            String top    = "╔═══════╦═════════════════╦═════════════════╦════════════════╗";
            String middle = "╠═══════╬═════════════════╬═════════════════╬════════════════╣";
            String bottom = "╚═══════╩═════════════════╩═════════════════╩════════════════╝";

            System.out.println(top);
            System.out.printf("║ %-5s ║ %-15s ║ %-15s ║ %-14s ║%n", "ID", "Nome", "Sobrenome", "CPF");
            System.out.println(middle);

            System.out.printf("║ %-5d ║ %-15s ║ %-15s ║ %-14s ║%n", 
                customer.getIdCustomer(), 
                customer.getFirstName(), 
                customer.getLastName(),
                customer.getCpf()
            );

            System.out.println(bottom); 
          }
    }
    public void TableCustomerNotId(Customer customer){

        if (customer != null) {
            String top    = "╔═════════════════╦═════════════════╦════════════════╗";
            String middle = "╠═════════════════╬═════════════════╬════════════════╣";
            String bottom = "╚═════════════════╩═════════════════╩════════════════╝";

            System.out.println(top);
            System.out.printf("║ %-15s ║ %-15s ║ %-14s ║%n", "Nome", "Sobrenome", "CPF");
            System.out.println(middle);
            
            System.out.printf("║ %-15s ║ %-15s ║ %-14s ║%n", 
                customer.getFirstName(), 
                customer.getLastName(),
                customer.getCpf()
            );
            
            System.out.println(bottom);
        } else {
            ErroCustomerNotFound();
        }
    }
    public void displayAllCustomers(List<Customer> customers) {
        String top    = "╔═══════╦═════════════════╦═════════════════╦════════════════╗";
        String middle = "╠═══════╬═════════════════╬═════════════════╬════════════════╣";
        String bottom = "╚═══════╩═════════════════╩═════════════════╩════════════════╝";

        System.out.println(top);
        System.out.printf("║ %-5s ║ %-15s ║ %-15s ║ %-14s ║%n", "ID", "Nome", "Sobrenome", "CPF");
        System.out.println(middle);
        for (Customer c : customers) {
            System.out.printf("║ %-5d ║ %-15s ║ %-15s ║ %-14s ║%n", 
                c.getIdCustomer(), 
                c.getFirstName(), 
                c.getLastName(),
                c.getCpf()
            );
        }
        System.out.println(bottom);
    }
    //verificaçã0
    public int check(){
        int opcao = 0;
        System.out.println("------------------------------");
        System.out.println(
            "[1] sim\n"+
            "[2] nao"  
        );

       try {
            System.out.print("➤ Digite opcao: ");
            opcao = Integer.parseInt(console.readLine());
            if(opcao < 1 || opcao > 2){
               System.err.println("➤➤ Invalido ➤➤");
               check();
            }
       } catch (Exception e) {
            System.err.println("➤➤ Digite decimal ➤➤");
            check();
       }
       
        return opcao;
    }
    public void BackMenu(){
        System.out.println("------------------------------");
        System.out.println("➤ Deseja Volta ao menu?");
        if(check() == 1){
            DisplayCustomerMenu();
        }else{
            iniciaSistema();
        }
    }
    //mensagens de Erros
    public void ErroCustomerNotFound(){
        System.out.println("➤➤ Cliente não existe ➤➤");
        System.out.println("------------------------------");
    }  
    public void ErroCustomerNotUpdate(){
        System.out.println("➤➤ Cliente não atualizado ➤➤");
        System.out.println("------------------------------");
    }
    public void ErroCustomerNotDelete(){
        System.out.println("➤➤ Cliente não Deletado ➤➤");
        System.out.println("------------------------------");
    }
    public void ErroCustomerNotRegistered(){
        System.out.println("------------------------------");
        System.out.println("➤➤ Cliente não Cadastrado ➤➤");
        System.out.println("------------------------------");
    }
    public void ErroOptionFound(){
        System.out.println("➤➤ Opcao invalida ➤➤");
        System.out.println("------------------------------");
    }
    public void ErrEmptyList(){
        System.out.println("➤➤ Lista vazia ➤➤");
        System.out.println("------------------------------");
    }
    public void ErrDraw(){
        System.out.println("➤➤ Não foi possivel sacar");
        System.out.println("------------------------------");
    }
    public void ErrDeposit(){
        System.out.printf("➤➤ Não foi possivel depositar");
        System.out.println("------------------------------");
    }

    //mensagens de sucesso
    public void SuccessCustomerUpdate(){
        System.out.println("➤➤ Cliente Atualizado ➤➤");
        System.out.println("------------------------------");
    }
    public void SuccessCustomerDelete(){
        System.out.println("➤➤ Cliente Deletado ➤➤");
        System.out.println("------------------------------");
    }
    public void SuccessCustomerRegistered(){
        System.out.println("------------------------------");
        System.out.println("➤➤ Cliente Cadastrado ➤➤");
        System.out.println("------------------------------");
    }
    public void SucessDraw(BigDecimal value){
        System.out.printf("➤➤ Valor de R$ %.2f foi sacado ➤➤\n", value);
        System.out.println("------------------------------");

    }
    public void SuccessDeposit(BigDecimal value){
        System.out.printf("➤➤ Valor de R$ %.2f foi depositado ➤➤\n", value);
        System.out.println("------------------------------");
    }

    public void ExitSystem(){
        System.out.println("➤➤ Sistema Encerrado ➤➤");
        System.out.println("------------------------------");
        System.exit(0);
    }
}
