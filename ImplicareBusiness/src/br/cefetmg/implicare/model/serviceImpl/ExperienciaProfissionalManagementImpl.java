/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.ExperienciaProfissionalDao;
import br.cefetmg.implicare.model.daoImpl.ExperienciaProfissionalDaoImpl;
import br.cefetmg.implicare.model.domain.ExperienciaProfissional;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ExperienciaProfissionalManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ExperienciaProfissionalManagementImpl implements ExperienciaProfissionalManagement {
    private final ExperienciaProfissionalDao ExperienciaProfissionalDao;
    
    public ExperienciaProfissionalManagementImpl(){
        ExperienciaProfissionalDao = new ExperienciaProfissionalDaoImpl();
    }
    
    @Override
    public void insert(ExperienciaProfissional ExperienciaProfissional) throws BusinessException, PersistenceException {
        ExperienciaProfissionalDao.insert(ExperienciaProfissional);
    }

    @Override
    public boolean update(Long CPF, int Seq_Experiencia, int Cod_Cargo, ExperienciaProfissional ExperienciaProfssional) throws BusinessException, PersistenceException {
        boolean result = ExperienciaProfissionalDao.update(CPF, Seq_Experiencia, Cod_Cargo, ExperienciaProfssional);
        return result;
    }

    @Override
    public boolean delete(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException {
        boolean result = ExperienciaProfissionalDao.delete(CPF, Seq_Experiencia, Cod_Cargo);
        return result;
    }

    @Override
    public List<ExperienciaProfissional> getExperienciasProfissionais(Long CPF) throws PersistenceException {
        List<ExperienciaProfissional> result = ExperienciaProfissionalDao.getExperienciasProfissionais(CPF);
        return result;
    }

    @Override
    public ExperienciaProfissional getExperienciaProfissionalCod(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException {
        ExperienciaProfissional result = ExperienciaProfissionalDao.getExperienciaProfissionalCod(CPF, Seq_Experiencia, Cod_Cargo);
        return result;
    }

}
