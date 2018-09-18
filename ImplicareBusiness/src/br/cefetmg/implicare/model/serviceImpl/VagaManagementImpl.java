/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.VagaDao;
import br.cefetmg.implicare.model.daoImpl.VagaDaoImpl;
import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.VagaManagement;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class VagaManagementImpl implements VagaManagement {
    private final VagaDao VagaDao;
    
    public VagaManagementImpl(){
        VagaDao = new VagaDaoImpl();
    }

    @Override
    public void insert(Vaga Vaga) throws BusinessException, PersistenceException {
        VagaDao.insert(Vaga);
    }

    @Override
    public boolean update(long CNPJ, int Cod_Cargo, Date Dat_Publicacao, Vaga Vaga) throws BusinessException, PersistenceException {
        boolean result = VagaDao.update(CNPJ, Cod_Cargo, Dat_Publicacao, Vaga);
        return result;
    }

    @Override
    public boolean delete(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        boolean result = VagaDao.delete(CNPJ, Cod_Cargo, Dat_Publicacao);
        return result;
    }

    @Override
    public List<Vaga> getVagaCNPJ(long CNPJ) throws PersistenceException {
        List<Vaga> result = VagaDao.getVagaCNPJ(CNPJ);
        return result;
    }

    @Override
    public List<Vaga> getVagaCod_Cargo(List<CargoInteresse> CarInteresse) throws PersistenceException {
        List<Vaga> result = VagaDao.getVagaCod_Cargo(CarInteresse);
        return result;
    }

    @Override
    public Vaga getVagaCod(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        Vaga result = VagaDao.getVagaCod(CNPJ, Cod_Cargo, Dat_Publicacao);
        return result;
    }
    
}
