/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.dao;

import br.cefetmg.implicare.model.domain.ExperienciaProfissional;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ExperienciaProfissionalDao {
    public void insert(ExperienciaProfissional ExperienciaProfissional) throws PersistenceException;
    public boolean update(Long CPF, int Seq_Experiencia, int Cod_Cargo, ExperienciaProfissional ExperienciaProfssional) throws PersistenceException;
    public boolean delete(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException;
    public List<ExperienciaProfissional> getExperienciasProfissionais(Long CPF) throws PersistenceException;
    public ExperienciaProfissional getExperienciaProfissionalCod(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException;
}
