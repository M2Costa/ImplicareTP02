/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CompetenciaDao;
import br.cefetmg.implicare.model.domain.Competencia;
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
public class CompetenciaDaoImpl implements CompetenciaDao {

    @Override
    public List<Competencia> listAll() throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Competencia ORDER BY Nom_Competencia;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Competencia> listAll = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    Competencia Comp = new Competencia();
                    Comp.setCod_Competencia(rs.getInt("Cod_Competencia"));
                    Comp.setNom_Competencia(rs.getString("Nom_Area_Estudo"));
                    listAll.add(Comp);
                } while (rs.next());
            }

            rs.close();
            ps.close();
            connection.close();

            return listAll;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Competencia getCompetenciaCod(int Cod_Competencia) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Competencia WHERE Cod_Competencia = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Cod_Competencia);
            ResultSet rs = ps.executeQuery();

            Competencia Comp = new Competencia();
            
            if (rs.next()) {
                Comp.setCod_Competencia(rs.getInt("Cod_Competencia"));
                Comp.setNom_Competencia(rs.getString("Nom_Competencia"));
            }

            rs.close();
            ps.close();
            connection.close();

            return Comp;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
