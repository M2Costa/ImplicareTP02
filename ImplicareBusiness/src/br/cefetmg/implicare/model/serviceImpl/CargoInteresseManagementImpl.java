/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CargoInteresseDao;
import br.cefetmg.implicare.model.daoImpl.CargoInteresseDaoImpl;
import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoInteresseManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CargoInteresseManagementImpl implements CargoInteresseManagement {
    private final CargoInteresseDao CargoInteresseDao;
    
    public CargoInteresseManagementImpl(){
        CargoInteresseDao = new CargoInteresseDaoImpl();
    }
    
    @Override
    public void insert(CargoInteresse CargoInteresse) throws BusinessException, PersistenceException {
        CargoInteresseDao.insert(CargoInteresse);
    }

    @Override
    public boolean delete(long CPF, int Cod_Cargo) throws PersistenceException {
        boolean result = CargoInteresseDao.delete(CPF, Cod_Cargo);
        return result;
    }

    @Override
    public List<CargoInteresse> getCargosInteresse(long CPF) throws PersistenceException {
        List<CargoInteresse> result = CargoInteresseDao.getCargosInteresse(CPF);
        return result;
    }

    @Override
    public CargoInteresse getCargoInteresseCod(long CPF, int Cod_Cargo) throws PersistenceException {
        CargoInteresse result = CargoInteresseDao.getCargoInteresseCod(CPF, Cod_Cargo);
        return result;
    }
    
}
