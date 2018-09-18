/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CandidatoVagaDao;
import br.cefetmg.implicare.model.daoImpl.CandidatoVagaDaoImpl;
import br.cefetmg.implicare.model.domain.CandidatoVaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CandidatoVagaManagement;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CandidatoVagaManagementImpl implements CandidatoVagaManagement {
    private final CandidatoVagaDao CandidatoVagaDao;
    
    public CandidatoVagaManagementImpl(){
        CandidatoVagaDao = new CandidatoVagaDaoImpl();
    }
    
    @Override
    public void insert(CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        CandidatoVagaDao.insert(CandidatoVaga);
    }

    @Override
    public boolean update(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        boolean result = CandidatoVagaDao.update(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, CandidatoVaga);
        return result;
    }

    @Override
    public List<CandidatoVaga> getCandidatosVaga(int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        List<CandidatoVaga> result = CandidatoVagaDao.getCandidatosVaga(Cod_Cargo, CNPJ, Dat_Publicacao);
        return result;
    }

    @Override
    public CandidatoVaga getCandidatoVagaCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        CandidatoVaga result = CandidatoVagaDao.getCandidatoVagaCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao);
        return result;
    }
    
}
