/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.Empresa;
import br.cefetmg.implicare.model.exception.PersistenceException;

/**
 *
 * @author Gabriel
 */
public interface EmpresaDao {
    public void insert(Empresa Empresa) throws PersistenceException;
    public boolean update(Long CNPJ,Empresa Empresa) throws PersistenceException;
    public Empresa getEmpresaCod(Long CNPJ) throws PersistenceException;
}
