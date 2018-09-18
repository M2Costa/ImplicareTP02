/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CandidatoVagaDialogoDao;
import br.cefetmg.implicare.model.domain.CandidatoVagaDialogo;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CandidatoVagaDialogoDaoImpl implements CandidatoVagaDialogoDao {

    @Override
    public void insert(CandidatoVagaDialogo CandidatoVagaDialogo) throws PersistenceException {
        try {
            Long Seq_CandidatoVagaDialogo;
                    
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO CandidatoVagaDialogo (CPF, Cod_Cargo, CNPJ,"
                    + "Dat_Publicacao, Dat_Dialogo, Txt_Dialogo, Idt_Empresa_Candidato) "
                    + "VALUES(?,?,?,?,?,?,?) RETURNING Seq_CandidatoVagaDialogo";

            PreparedStatement ps = connection.prepareStatement(sql);
           
            ps.setLong(1, CandidatoVagaDialogo.getCPF());
            ps.setInt(2, CandidatoVagaDialogo.getCod_Cargo());
            ps.setLong(3, CandidatoVagaDialogo.getCNPJ());
            ps.setDate(4, CandidatoVagaDialogo.getDat_Publicacao());
            ps.setTimestamp(5, CandidatoVagaDialogo.getDat_Dialogo());
            ps.setString(6, CandidatoVagaDialogo.getTxt_Dialogo());
            ps.setString(7, CandidatoVagaDialogo.getIdt_Empresa_Candidato());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Seq_CandidatoVagaDialogo = rs.getLong("Seq_CandidatoVagaDialogo");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public List<CandidatoVagaDialogo> getCandidatoVagaDialogo(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM CandidatoVagaDialogo WHERE CPF = ?, Cod_Cargo = ?, CNPJ = ?, Dat_Publicacao = ? "
                    + "ORDER BY Dat_Dialogo;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Cargo);
            ps.setLong(3, CNPJ);
            ps.setDate(4, Dat_Publicacao);
            ResultSet rs = ps.executeQuery();

            List<CandidatoVagaDialogo> CandidatoDialogo = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    CandidatoVagaDialogo CandDialogo= new CandidatoVagaDialogo();
                    
                    CandDialogo.setCPF(rs.getLong("CPF"));
                    CandDialogo.setCod_Cargo(rs.getInt("Cod_Cargo"));
                    CandDialogo.setCNPJ(rs.getLong("CNPJ"));
                    CandDialogo.setDat_Publicacao(rs.getDate("Dat_Publicacao"));
                    CandDialogo.setDat_Dialogo(rs.getTimestamp("Dat_Dialogo"));
                    CandDialogo.setTxt_Dialogo(rs.getString("Txt_Dialogo"));
                    CandDialogo.setIdt_Empresa_Candidato(rs.getString("Idt_Empresa_Candidato"));
                    
                    CandidatoDialogo.add(CandDialogo);
                } while (rs.next());
            }

            rs.close();
            ps.close();
            connection.close();

            return CandidatoDialogo;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public CandidatoVagaDialogo getCandidatoVagaDialogoCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, Timestamp Dat_Dialogo) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

             String sql = "SELECT * FROM CandidatoVagaDialogo WHERE CPF = ?, Cod_Cargo = ?, CNPJ = ?, "
                     + "Dat_Publicacao = ?, Dat_Dialogo = ? ORDER BY Dat_Dialogo;";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Cargo);
            ps.setLong(3, CNPJ);
            ps.setDate(4, Dat_Publicacao);
            ps.setTimestamp(5, Dat_Dialogo);
            
            ResultSet rs = ps.executeQuery();

            CandidatoVagaDialogo CandDialogo = new CandidatoVagaDialogo();
            
            if (rs.next()) {
                CandDialogo.setCPF(rs.getLong("CPF"));
                CandDialogo.setCod_Cargo(rs.getInt("Cod_Cargo"));
                CandDialogo.setCNPJ(rs.getLong("CNPJ"));
                CandDialogo.setDat_Publicacao(rs.getDate("Dat_Publicacao"));
                CandDialogo.setDat_Dialogo(rs.getTimestamp("Dat_Dialogo"));
                CandDialogo.setTxt_Dialogo(rs.getString("Txt_Dialogo"));
                CandDialogo.setIdt_Empresa_Candidato(rs.getString("Idt_Empresa_Candidato"));
            }

            rs.close();
            ps.close();
            connection.close();

            return CandDialogo;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
