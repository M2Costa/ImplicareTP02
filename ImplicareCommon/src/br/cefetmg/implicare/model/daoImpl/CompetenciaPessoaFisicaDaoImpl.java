/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CompetenciaPessoaFisicaDao;
import br.cefetmg.implicare.model.domain.CompetenciaPessoaFisica;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CompetenciaPessoaFisicaDaoImpl implements CompetenciaPessoaFisicaDao {

    @Override
    public void insert(CompetenciaPessoaFisica CompetenciaPessoaFisica) throws PersistenceException {
        try {
            Long Seq_CompetenciaPessoaFisica;
                    
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO CompetenciaPessoaFisica (CPF, Cod_Competencia, Cod_Proficiencia) "
                    + "VALUES(?,?,?) RETURNING Seq_CompetenciaPessoaFisica";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setLong(1, CompetenciaPessoaFisica.getCPF());
            ps.setInt(2, CompetenciaPessoaFisica.getCod_Competencia());
            ps.setInt(3, CompetenciaPessoaFisica.getCod_Proficiencia());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Seq_CompetenciaPessoaFisica = rs.getLong("Seq_CompetenciaPessoaFisica");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean delete(long CPF, int Cod_Competencia) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            
            String SQL = "DELETE FROM CompetenciaPessoaFisica"
                    + "WHERE CPF = ?, Cod_Competencia = ?";
            
            PreparedStatement ps = connection.prepareStatement(SQL);
            
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Competencia);
            
            ps.executeQuery(SQL);
            ps.close();
            connection.close();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public List<CompetenciaPessoaFisica> getCompetenciasPessoaFisica(long CPF) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM CompetenciaPessoaFisica WHERE CPF = ? ORDER BY Cod_Competencia;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, CPF);
            ResultSet rs = ps.executeQuery();

            List<CompetenciaPessoaFisica> Competencia = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    CompetenciaPessoaFisica Comp = new CompetenciaPessoaFisica();
                    
                    Comp.setCPF(rs.getLong("CPF"));
                    Comp.setCod_Competencia(rs.getInt("Cod_Competencia"));
                    Comp.setCod_Proficiencia(rs.getInt("Cod_Proficiencia"));
                    
                    Competencia.add(Comp);
                } while (rs.next());
            }

            rs.close();
            ps.close();
            connection.close();

            return Competencia;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public CompetenciaPessoaFisica getCompetenciaPessoaFisicaCod(long CPF, int Cod_Competencia) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM CompetenciaPessoaFisica WHERE CPF = ?, Cod_Competencia = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Competencia);
            
            ResultSet rs = ps.executeQuery();

            CompetenciaPessoaFisica CompPessoa = new CompetenciaPessoaFisica();
            
            if (rs.next()) {
                CompPessoa.setCPF(rs.getLong("CPF"));
                CompPessoa.setCod_Competencia(rs.getInt("Cod_Competencia"));
                CompPessoa.setCod_Proficiencia(rs.getInt("Cod_Proficiencia"));
            }

            rs.close();
            ps.close();
            connection.close();

            return CompPessoa;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
