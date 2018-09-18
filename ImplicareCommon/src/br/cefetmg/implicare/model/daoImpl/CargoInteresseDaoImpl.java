/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CargoInteresseDao;
import br.cefetmg.implicare.model.domain.CargoInteresse;
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
public class CargoInteresseDaoImpl implements CargoInteresseDao {

    @Override
    public void insert(CargoInteresse CargoInteresse) throws PersistenceException {
        try {
            Long Seq_CargoInteresse;
                    
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO CargoInteresse (CPF, Cod_Cargo) "
                    + "VALUES(?,?) RETURNING Seq_CargoInteresse";

            PreparedStatement ps = connection.prepareStatement(sql);
           
            ps.setLong(1, CargoInteresse.getCPF());
            ps.setInt(2, CargoInteresse.getCod_Cargo());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Seq_CargoInteresse = rs.getLong("Seq_CargoInteresse");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean delete(long CPF, int Cod_Cargo) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            
            String SQL = "DELETE FROM CargoInteresse"
                    + "WHERE CPF = ?, Cod_Cargo = ?";
            
            PreparedStatement ps = connection.prepareStatement(SQL);
            
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Cargo);
            
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
    public List<CargoInteresse> getCargosInteresse(long CPF) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM CargoInteresse WHERE CPF = ? ORDER BY Cod_Cargo";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, CPF);
            ResultSet rs = ps.executeQuery();

            List<CargoInteresse> CargoInt = new ArrayList<>();
            
            if (rs.next()) {
                do {
                    CargoInteresse Car = new CargoInteresse();
                    Car.setCPF(rs.getLong("CPF"));
                    Car.setCod_Cargo(rs.getInt("Cod_Cargo"));
                    CargoInt.add(Car);
                } while (rs.next());
            }

            rs.close();
            ps.close();
            connection.close();

            return CargoInt;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public CargoInteresse getCargoInteresseCod(long CPF, int Cod_Cargo) throws PersistenceException {
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM CargoInteresse WHERE CPF = ?, Cod_Cargo = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, CPF);
            ps.setInt(2, Cod_Cargo);
            ResultSet rs = ps.executeQuery();

            CargoInteresse CarInteresse = new CargoInteresse();
            
            if (rs.next()) {
                CarInteresse.setCPF(rs.getLong("CPF"));
                CarInteresse.setCod_Cargo(rs.getInt("Cod_Cargo"));
            }

            rs.close();
            ps.close();
            connection.close();

            return CarInteresse;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
