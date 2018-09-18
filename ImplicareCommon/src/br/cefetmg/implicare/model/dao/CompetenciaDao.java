/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.Competencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface CompetenciaDao {
    public List<Competencia> listAll() throws PersistenceException;
    public Competencia getCompetenciaCod(int Cod_Competencia) throws PersistenceException;
}
