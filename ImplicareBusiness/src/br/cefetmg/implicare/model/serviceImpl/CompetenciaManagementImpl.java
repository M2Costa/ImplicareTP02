/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CompetenciaDao;
import br.cefetmg.implicare.model.daoImpl.CompetenciaDaoImpl;
import br.cefetmg.implicare.model.domain.Competencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CompetenciaManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CompetenciaManagementImpl implements CompetenciaManagement {
    private final CompetenciaDao CompetenciaDao;
    
    public CompetenciaManagementImpl(){
        CompetenciaDao = new CompetenciaDaoImpl();
    }
    
    @Override
    public List<Competencia> listAll() throws PersistenceException {
        List<Competencia> result = CompetenciaDao.listAll();
        return result;
    }

    @Override
    public Competencia getCompetenciaCod(int Cod_Competencia) throws PersistenceException {
        Competencia result = CompetenciaDao.getCompetenciaCod(Cod_Competencia);
        return result;
    }
    
}
