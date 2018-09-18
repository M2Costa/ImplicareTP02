/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.ProficienciaDao;
import br.cefetmg.implicare.model.domain.Proficiencia;
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
public class ProficienciaDaoImpl implements ProficienciaDao {

    @Override
    public List<Proficiencia> listAll() throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Proficiencia ORDER BY Nivel_Proficiencia;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Proficiencia> listAll = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    Proficiencia Prof = new Proficiencia();
                    Prof.setCod_Proficiencia(rs.getInt("Cod_Proficiencia"));
                    Prof.setNivel_Proficiencia(rs.getString("Nivel_Proficiencia"));
                    listAll.add(Prof);
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
    public Proficiencia getProficienciaCod(int Cod_Proficiencia) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Proficiencia WHERE Cod_Proficiencia = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, Cod_Proficiencia);
            ResultSet rs = ps.executeQuery();

            Proficiencia Prof = new Proficiencia();
            
            if (rs.next()) {
                Prof.setCod_Proficiencia(rs.getInt("Cod_Proficiencia"));
                Prof.setNivel_Proficiencia(rs.getString("Nivel_Proficiencia"));
            }

            rs.close();
            ps.close();
            connection.close();

            return Prof;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
