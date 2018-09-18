/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CargoDao;
import br.cefetmg.implicare.model.domain.Cargo;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class CargoDaoImpl implements CargoDao{

    @Override
    public List<Cargo> listAll() throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Cargo ORDER BY Nom_Cargo;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Cargo> listAll = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    Cargo Car = new Cargo();
                    Car.setCod_Cargo(rs.getInt("Cod_Cargo"));
                    Car.setNom_Cargo(rs.getString("Nom_Cargo"));
                    listAll.add(Car);
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
    public List<Cargo> getCargos(Set<CargoAreaEstudo> CargoArea) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            List<Cargo> Cargo = new ArrayList<>();
            
            for (CargoAreaEstudo CarArea : CargoArea) {
                String sql = "SELECT * FROM CompetenciaPessoaFisica WHERE Cod_Cargo = ?";
                
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, CarArea.getCod_Area_Estudo());
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    Cargo Car = new Cargo();
                    
                    Car.setCod_Cargo(rs.getInt("Cod_Cargo"));
                    Car.setNom_Cargo(rs.getString("Nom_Cargo"));
                    
                    Cargo.add(Car);
                }
                
                rs.close();
                ps.close();
            }
            
            connection.close();

            return Cargo;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    @Override
    public Cargo getCargoCod(int Cod_Cargo) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Cargo WHERE Cod_Cargo = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Cod_Cargo);
            ResultSet rs = ps.executeQuery();

            Cargo Car = new Cargo();
            
            if (rs.next()) {
                Car.setCod_Cargo(rs.getInt("Cod_Cargo"));
                Car.setNom_Cargo(rs.getString("Nom_Cargo"));
            }

            rs.close();
            ps.close();
            connection.close();

            return Car;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
