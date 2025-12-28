package com.br.felipe.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.felipe.Model.Account;
import com.br.felipe.Model.Connector;

public class AccountRepository {
    
    public void Create(Account account){
        var sql = "insert into Account (Balance, Customer_id) values (?, ?)";
        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setBigDecimal(1, account.getBalance());
            stm.setInt(2, account.getCustomer_id());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e);
        }
    }

    public List<Account> ReadALL(){
        var sql = "select Account_id, Balance, Customer_id from Account";
        List<Account> Accounts = new ArrayList<>();
        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            if (rs != null){
                while(rs.next()){
                    Account account = new Account(
                        rs.getInt("Account_id"), 
                        rs.getBigDecimal("Balance"), 
                        rs.getInt("Customer_id"));

                    Accounts.add(account);
                }
            }


        } catch (SQLException e) {
            System.err.print(e);
        }

        return Accounts;
    }

    public Account ReadOne(int id){
        var sql = "select Account_id, Balance, Customer_id from Account where Account_id = ?";
        Account account = new Account();
        
        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                account = new Account(
                    rs.getInt("Account_id"),
                    rs.getBigDecimal("Balance"), 
                    rs.getInt("Customer_id")
                );
                return account;
            }
            
        } catch (SQLException e) {
            System.err.print(e);
        }

        return null;
    }

    public boolean Update(int customer_id, BigDecimal value){
        var sql = "Update Account set balance = ? where Account_id = ? ";

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setBigDecimal(1, value);
            stm.setInt(2, customer_id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print(e);
            return false;
        }
    }

    public void Delete(int id){
        var sql = "delete from Account where Account_id = ?";

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e);
        }
    }
}
