/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Estado;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.EstadoManagement;
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

public class EstadoSocketProxy implements EstadoManagement {
    
    Cliente Cliente;
    
    public EstadoSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(EstadoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(EstadoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Estado> listAll() throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        pacoteEnviado = new Pacote(TipoOperacao.LIST_Estados, null);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Estado> Est = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Estado>>() {
                }.getType());
        
        return Est;
    }

    @Override
    public Estado getEstadoCod(int Cod_Estado) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Cod_Estado));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Estado, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Estado Est = gson.fromJson(pacoteRecebido.getDados().get(0), Estado.class);
        return Est;
    }
    
}
