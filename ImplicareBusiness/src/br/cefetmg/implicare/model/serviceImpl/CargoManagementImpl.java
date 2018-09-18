/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CargoDao;
import br.cefetmg.implicare.model.daoImpl.CargoDaoImpl;
import br.cefetmg.implicare.model.domain.Cargo;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoManagement;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class CargoManagementImpl implements CargoManagement {
    private final CargoDao CargoDao;
    
    public CargoManagementImpl(){
        CargoDao = new CargoDaoImpl();
    }
    
    @Override
    public List<Cargo> listAll() throws PersistenceException {
        List<Cargo> result = CargoDao.listAll();
        return result;
    }
    
     @Override
    public List<Cargo> getCargos(Set<CargoAreaEstudo> CargoArea) throws PersistenceException {
        List<Cargo> result = CargoDao.getCargos(CargoArea);
        return result;
    }
    
    @Override
    public Cargo getCargoCod(int Cod_Cargo) throws PersistenceException {
        Cargo result = CargoDao.getCargoCod(Cod_Cargo);
        return result;
    }

}
