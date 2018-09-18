/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CandidatoVagaDialogoDao;
import br.cefetmg.implicare.model.daoImpl.CandidatoVagaDialogoDaoImpl;
import br.cefetmg.implicare.model.domain.CandidatoVagaDialogo;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CandidatoVagaDialogoManagement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CandidatoVagaDialogoManagementImpl implements CandidatoVagaDialogoManagement {
    private final CandidatoVagaDialogoDao CandidatoVagaDialogoDao;
    
    public CandidatoVagaDialogoManagementImpl(){
        CandidatoVagaDialogoDao = new CandidatoVagaDialogoDaoImpl();
    }
    
    @Override
    public void insert(CandidatoVagaDialogo CandidatoVagaDialogo) throws BusinessException, PersistenceException {
        CandidatoVagaDialogoDao.insert(CandidatoVagaDialogo);
    }

    @Override
    public List<CandidatoVagaDialogo> getCandidatoVagaDialogo(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        List<CandidatoVagaDialogo> result = CandidatoVagaDialogoDao.getCandidatoVagaDialogo(CPF, Cod_Cargo, CNPJ, Dat_Publicacao);
        return result;
    }

    @Override
    public CandidatoVagaDialogo getCandidatoVagaDialogoCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, Timestamp Dat_Dialogo) throws PersistenceException {
        CandidatoVagaDialogo result = CandidatoVagaDialogoDao.getCandidatoVagaDialogoCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, Dat_Dialogo);
        return result;
    }
    
}
