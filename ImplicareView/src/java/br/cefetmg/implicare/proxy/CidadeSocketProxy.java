/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Cidade;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CidadeManagement;
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

public class CidadeSocketProxy implements CidadeManagement {
    
    Cliente Cliente;
    
    public CidadeSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(CidadeSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CidadeSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Cidade> getCidades(int Cod_Estado) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Cod_Estado));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_Cidades, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Cidade> CandVaga = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Cidade>>() {
                }.getType());
        
        return CandVaga;
    }

    @Override
    public Cidade getCidadeCod(int Cod_Cidade) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(Cod_Cidade));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Cidade, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Cidade Cid = gson.fromJson(pacoteRecebido.getDados().get(0), Cidade.class);
        return Cid;
    }
    
}
