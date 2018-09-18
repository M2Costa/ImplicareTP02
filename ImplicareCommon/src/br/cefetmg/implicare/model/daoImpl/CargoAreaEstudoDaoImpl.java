/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.daoImpl;

import br.cefetmg.implicare.model.dao.CargoAreaEstudoDao;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class CargoAreaEstudoDaoImpl implements CargoAreaEstudoDao {

    @Override
    public Set<CargoAreaEstudo> CargoAreaEstudo(List<FormacaoAcademica> FormAcad) throws PersistenceException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            Set<CargoAreaEstudo> CargoArea = new HashSet<>();
            
            for (FormacaoAcademica Form : FormAcad) {
                String sql = "SELECT * FROM CompetenciaPessoaFisica WHERE Cod_Area_Estudo = ? ORDER BY Cod_Cargo";
                
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, Form.getCod_Area_Estudo());
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    do {
                        CargoAreaEstudo Cargo = new CargoAreaEstudo();

                        Cargo.setCod_Cargo(rs.getInt("Cod_Cargo"));

                        CargoArea.add(Cargo);
                    } while (rs.next());
                }
                
                rs.close();
                ps.close();
            }
            
            connection.close();

            return CargoArea;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
