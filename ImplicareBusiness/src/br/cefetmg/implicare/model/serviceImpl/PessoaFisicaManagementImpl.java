/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.PessoaFisicaDao;
import br.cefetmg.implicare.model.daoImpl.PessoaFisicaDaoImpl;
import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.PessoaFisicaManagement;

/**
 *
 * @author Gabriel
 */
public class PessoaFisicaManagementImpl implements PessoaFisicaManagement {
    private final PessoaFisicaDao PessoaFisicaDao;
    
    public PessoaFisicaManagementImpl(){
        PessoaFisicaDao = new PessoaFisicaDaoImpl(); 
    }
    
    @Override
    public void insert(PessoaFisica PessoaFisica) throws BusinessException, PersistenceException {
        PessoaFisicaDao.insert(PessoaFisica);
    }

    @Override
    public boolean update(Long CPF, PessoaFisica PessoaFisica) throws BusinessException, PersistenceException {
        boolean result = PessoaFisicaDao.update(CPF, PessoaFisica);
        return result;
    }

    @Override
    public PessoaFisica getPessoaFisicaCod(Long CPF) throws PersistenceException {
        PessoaFisica result = PessoaFisicaDao.getPessoaFisicaCod(CPF);
        return result;
    }
    
}
