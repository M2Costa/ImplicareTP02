/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.serviceImpl;

import br.cefetmg.implicare.model.dao.CidadeDao;
import br.cefetmg.implicare.model.daoImpl.CidadeDaoImpl;
import br.cefetmg.implicare.model.domain.Cidade;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CidadeManagement;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CidadeManagementImpl implements CidadeManagement {
    private final CidadeDao CidadeDao;
    
    public CidadeManagementImpl(){
        CidadeDao = new CidadeDaoImpl();
    }
    
    @Override
    public List<Cidade> getCidades(int Cod_Estado) throws PersistenceException {
        List<Cidade> result = CidadeDao.getCidades(Cod_Estado);
        return result;
    }

    @Override
    public Cidade getCidadeCod(int Cod_Cidade) throws PersistenceException {
        Cidade result = CidadeDao.getCidadeCod(Cod_Cidade);
        return result;
    }
    
}
