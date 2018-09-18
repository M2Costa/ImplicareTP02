/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ProficienciaDao {
    public List<Proficiencia> listAll() throws PersistenceException;
    public Proficiencia getProficienciaCod(int Cod_Proficiencia) throws PersistenceException;
}
