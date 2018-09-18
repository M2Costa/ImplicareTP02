/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.service;

import br.cefetmg.implicare.model.domain.CandidatoVagaDialogo;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Andre Matheus
 * @author Gabriel
 */
public interface CandidatoVagaDialogoManagement {
    public void insert(CandidatoVagaDialogo CandidatoVagaDialogo) throws BusinessException, PersistenceException;
    public List<CandidatoVagaDialogo> getCandidatoVagaDialogo(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException;
    public CandidatoVagaDialogo getCandidatoVagaDialogoCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, Timestamp Dat_Dialogo) throws PersistenceException;
}
