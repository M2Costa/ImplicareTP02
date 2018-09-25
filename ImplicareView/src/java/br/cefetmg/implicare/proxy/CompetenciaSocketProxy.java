/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Competencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CompetenciaManagement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */

public class CompetenciaSocketProxy implements CompetenciaManagement {
    
    Cliente Cliente;
    
    public CompetenciaSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(CompetenciaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CompetenciaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Competencia> listAll() throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        pacoteEnviado = new Pacote(TipoOperacao.LIST_Competencia, null);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Competencia> Comp = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Competencia>>() {
                }.getType());
        
        return Comp;
    }

    @Override
    public Competencia getCompetenciaCod(int Cod_Competencia) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(Cod_Competencia));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Competencia, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Competencia Comp = gson.fromJson(pacoteRecebido.getDados().get(0), Competencia.class);
        return Comp;
    }
    
}
