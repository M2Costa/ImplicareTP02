/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.Usuario;
import br.cefetmg.implicare.model.exception.PersistenceException;

/**
 *
 * @author Gabriel
 */
public interface UsuarioDao {
    public void insert(Usuario Usuario) throws PersistenceException;
    public boolean update(Long CPF_CNPJ, Usuario Usuario) throws PersistenceException;
    public Usuario getUsuarioCod(Long CPF_CNPJ) throws PersistenceException;
    public Usuario getLogin(Long CPF_CNPJ, String Senha) throws PersistenceException;
}
