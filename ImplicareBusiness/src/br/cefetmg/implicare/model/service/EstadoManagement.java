/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.service;

import br.cefetmg.implicare.model.domain.Estado;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Andre Matheus
 */
public interface EstadoManagement {
    public List<Estado> listAll() throws PersistenceException;
    public Estado getEstadoCod(int Cod_Estado) throws PersistenceException;
}
