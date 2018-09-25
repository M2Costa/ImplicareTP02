/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Competencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CompetenciaManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CompetenciaSocketProxy implements CompetenciaManagement {
    
    Cliente Cliente;
    
    public CompetenciaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public List<Competencia> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competencia getCompetenciaCod(int Cod_Competencia) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
