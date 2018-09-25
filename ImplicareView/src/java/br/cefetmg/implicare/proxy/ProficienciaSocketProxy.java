/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ProficienciaManagement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */

public class ProficienciaSocketProxy implements ProficienciaManagement {
    
    Cliente Cliente;
    
    public ProficienciaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public List<Proficiencia> listAll() throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        pacoteEnviado = new Pacote(TipoOperacao.LIST_Proficiencia, null);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Proficiencia> Prof = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Proficiencia>>() {
                }.getType());
        
        return Prof;
    }

    @Override
    public Proficiencia getProficienciaCod(int Cod_Proficiencia) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Cod_Proficiencia));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Proficiencia, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Proficiencia Prof = gson.fromJson(pacoteRecebido.getDados().get(0), Proficiencia.class);
        return Prof;
    }
    
}
