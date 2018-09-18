/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.exception.PersistenceException;

/**
 *
 * @author Gabriel
 */
public interface PessoaFisicaDao {
    public void insert(PessoaFisica PessoaFisica) throws PersistenceException;
    public boolean update(Long CPF, PessoaFisica PessoaFisica) throws PersistenceException;
    public PessoaFisica getPessoaFisicaCod(Long CPF) throws PersistenceException;
}
