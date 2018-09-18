/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CargoAreaEstudoDao;
import br.cefetmg.implicare.model.daoImpl.CargoAreaEstudoDaoImpl;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoAreaEstudoManagement;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class CargoAreaEstudoMangementImpl implements CargoAreaEstudoManagement {
    private final CargoAreaEstudoDao CargoAreaEstudoDao;
    
    public CargoAreaEstudoMangementImpl(){
        CargoAreaEstudoDao = new CargoAreaEstudoDaoImpl();
    }
    
    @Override
    public Set<CargoAreaEstudo> CargoAreaEstudo(List<FormacaoAcademica> FormAcad) throws BusinessException, PersistenceException {
        Set<CargoAreaEstudo> result = CargoAreaEstudoDao.CargoAreaEstudo(FormAcad);
        return result;
    }
    
}
