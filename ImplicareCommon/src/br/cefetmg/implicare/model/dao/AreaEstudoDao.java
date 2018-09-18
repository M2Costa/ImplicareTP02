/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.AreaEstudo;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface AreaEstudoDao {
    public List<AreaEstudo> listAll() throws PersistenceException;
    public AreaEstudo getAreaEstudoCod(int Cod_Area_Estudo) throws PersistenceException;
}
