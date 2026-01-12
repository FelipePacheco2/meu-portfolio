package com.br.felipe.Repository;

import java.sql.SQLException;

import com.br.felipe.Model.Connector;
import com.br.felipe.Model.RegisterAccount;

public class RegisterAccountRepository {
    
    public void create(RegisterAccount register){
        var sql = "insert into RegisterAccount (reason, amount, account_id) values (?, ?, ?)";

        try (var conexao = Connector.obterConexao()){
            var stm = conexao.prepareStatement(sql);
            stm.setString(1, register.getReason());
            stm.setBigDecimal(2, register.getValue());
            stm.setInt(3, register.getAccount_id());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
