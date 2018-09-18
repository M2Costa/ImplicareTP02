/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CandidatoVagaManagement;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CandidatoVaga implements CandidatoVagaManagement {

    @Override
    public void insert(br.cefetmg.implicare.model.domain.CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, br.cefetmg.implicare.model.domain.CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<br.cefetmg.implicare.model.domain.CandidatoVaga> getCandidatosVaga(int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public br.cefetmg.implicare.model.domain.CandidatoVaga getCandidatoVagaCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
