/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.ProficienciaDao;
import br.cefetmg.implicare.model.daoImpl.ProficienciaDaoImpl;
import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ProficienciaManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ProficienciaManagementImpl implements ProficienciaManagement {
    private final ProficienciaDao ProficienciaDao;
    
    public ProficienciaManagementImpl(){
        ProficienciaDao = new ProficienciaDaoImpl();
    }
    
    @Override
    public List<Proficiencia> listAll() throws PersistenceException {
        List<Proficiencia> result = ProficienciaDao.listAll();
        return result;
    }

    @Override
    public Proficiencia getProficienciaCod(int Cod_Proficiencia) throws PersistenceException {
        Proficiencia result = ProficienciaDao.getProficienciaCod(Cod_Proficiencia);
        return result;
    }
    
}
