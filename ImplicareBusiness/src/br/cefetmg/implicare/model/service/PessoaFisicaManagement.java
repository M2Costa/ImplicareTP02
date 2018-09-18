/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.service;

import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;

/**
 *
 * @author Andre Matheus
 */
public interface PessoaFisicaManagement {
    public void insert(PessoaFisica PessoaFisica) throws BusinessException, PersistenceException;
    public boolean update(Long CPF, PessoaFisica PessoaFisica) throws BusinessException, PersistenceException;
    public PessoaFisica getPessoaFisicaCod(Long CPF) throws PersistenceException;
}
