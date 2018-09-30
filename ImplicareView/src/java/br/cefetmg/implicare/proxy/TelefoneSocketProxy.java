/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Telefone;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.TelefoneManagement;
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

public class TelefoneSocketProxy implements TelefoneManagement {
    
    Cliente Cliente;
    
    public TelefoneSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(TelefoneSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelefoneSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(Telefone Telefone) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Telefone));
        pacoteEnviado = new Pacote(TipoOperacao.INS_Telefone, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(long CPF_CNPJ, String Num_Telefone, Telefone Telefone) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF_CNPJ));
        dados.add(gson.toJson(Num_Telefone));
        dados.add(gson.toJson(Telefone));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_Telefone, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public boolean delete(long CPF_CNPJ, String Num_Telefone) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF_CNPJ));
        dados.add(gson.toJson(Num_Telefone));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_Telefone, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<Telefone> getTelefones(long CPF_CNPJ) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF_CNPJ));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_Telefones, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Telefone> Tel = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Telefone>>() {
                }.getType());
        
        return Tel;
    }

    @Override
    public Telefone getTelefoneCod(long CPF_CNPJ, String Num_Telefone) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF_CNPJ));
        dados.add(gson.toJson(Num_Telefone));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Telefone, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Telefone Tel = gson.fromJson(pacoteRecebido.getDados().get(0), Telefone.class);
        return Tel;
    }
    
}
