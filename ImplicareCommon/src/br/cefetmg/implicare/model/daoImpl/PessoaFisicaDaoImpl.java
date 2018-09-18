/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.PessoaFisicaDao;
import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class PessoaFisicaDaoImpl implements PessoaFisicaDao {

    @Override
    public void insert(PessoaFisica PessoaFisica) throws PersistenceException {
        try {
            Long Seq_PessoaFisica;
                    
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Pessoa_Fisica (CPF, Nome, Data_Nascimento) "
                    + "VALUES(?,?,?) RETURNING Seq_Telefone";

            PreparedStatement ps = connection.prepareStatement(sql);
        
            ps.setLong(1, PessoaFisica.getCPF());
            ps.setString(2, PessoaFisica.getNome());
            ps.setDate(3, PessoaFisica.getData_Nascimento());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Seq_PessoaFisica = rs.getLong("Seq_PessoaFisica");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean update(Long CPF, PessoaFisica PessoaFisica) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();
            
            String SQL = "UPDATE PessoaFisica SET Nome = ?, Data_Nascimento = ?"
                    + "WHERE CPF = ?";
            
            PreparedStatement ps = connection.prepareStatement(SQL);
            
            ps.setString(1, PessoaFisica.getNome());
            ps.setDate(2, PessoaFisica.getData_Nascimento());
            ps.setLong(5, CPF);
            
            ps.executeQuery(SQL);
            ps.close();
            connection.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public PessoaFisica getPessoaFisicaCod(Long CPF) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM PessoaFisica WHERE CPF = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, CPF);
            ResultSet rs = ps.executeQuery();

            PessoaFisica Pessoa = new PessoaFisica();
            
            if (rs.next()) {
                Pessoa.setCPF(rs.getLong("CPF"));
                Pessoa.setNome(rs.getString("Nome"));
                Pessoa.setData_Nascimento(rs.getDate("Data_Nascimento"));
            }

            rs.close();
            ps.close();
            connection.close();

            return Pessoa;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
