/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.service;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Andre Matheus
 * @author Gabriel
 * 
 */
public interface CargoInteresseManagement {
    public void insert(CargoInteresse CargoInteresse) throws BusinessException, PersistenceException;
    public boolean delete(long CPF, int Cod_Cargo) throws PersistenceException;
    public List<CargoInteresse> getCargosInteresse(long CPF) throws PersistenceException;
    public CargoInteresse getCargoInteresseCod(long CPF, int Cod_Cargo) throws PersistenceException;
}
