package com.br.felipe.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.felipe.Model.Connector;
import com.br.felipe.Model.Customer;
import java.sql.Statement;

public class CustomerRepository {
    
    public int Create(Customer customer){

        if(cpfExists(customer.getCpf())){
            return 0;
        }


        var sqlInsert = "insert into Customer (firstName, lastName, cpf) values (?, ?, ?)";

        try (var conexao = Connector.obterConexao(); var stm = conexao.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, customer.getFirstName());
            stm.setString(2, customer.getLastName());
            stm.setString(3, customer.getCpf());
            stm.executeUpdate();

            try (ResultSet rs = stm.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return 0;
    }

    public List<Customer> ReadAll(){
        var sql = "Select customer_id, firstName, LastName, cpf from Customer";
        List<Customer> customers = new ArrayList<>();

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs != null){
                while (rs.next()) {
                    Customer customer = new Customer(
                        rs.getInt("Customer_id"), 
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("cpf")
                    );
                    customers.add(customer);
                }

                return customers;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return null;
    }

    public Customer ReadOne(int customer_id){
        
        var sql = "Select customer_id, firstName, LastName, cpf from Customer where customer_id = ?";

        Customer customer = new Customer();

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setInt(1, customer_id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                customer = new Customer(
                    rs.getInt("Customer_id"), 
                    rs.getString("firstName"), 
                    rs.getString("LastName"), 
                    rs.getString("cpf")
                );

                return customer;
            }
        } catch (SQLException e) {
            System.err.println(e);

        }

        return null;
    }   

    public void Update(Customer customer){
        var sql = "update Customer set firstName = ?, lastName = ?, cpf = ? where customer_id = ?";
        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setString(1, customer.getFirstName());
            stm.setString(2, customer.getLastName());
            stm.setString(3, customer.getCpf());
            stm.setInt(4, customer.getIdCustomer());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void Delete(int id){
        var sql = "delete from Customer where customer_id = ?";
        
        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public boolean cpfExists(String cpf){
        var sql = "Select count(*) from Customer where cpf = ?";

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setString(1, cpf);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
               return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }
}
