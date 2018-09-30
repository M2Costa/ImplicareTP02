/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.CompetenciaPessoaFisica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CompetenciaPessoaFisicaManagement;
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

public class CompetenciaPessoaFisicaSocketProxy implements CompetenciaPessoaFisicaManagement {
    
    Cliente Cliente;
    
    public CompetenciaPessoaFisicaSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(CompetenciaPessoaFisicaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CompetenciaPessoaFisicaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(CompetenciaPessoaFisica CompetenciaPessoaFisica) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CompetenciaPessoaFisica));
        pacoteEnviado = new Pacote(TipoOperacao.INS_CompetenciaPessoaFisica, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean delete(long CPF, int Cod_Competencia) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Cod_Competencia));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_CompetenciaPessoaFisica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<CompetenciaPessoaFisica> getCompetenciasPessoaFisica(long CPF) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_CompetenciaPessoaFisica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<CompetenciaPessoaFisica> CompPessoa = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<CompetenciaPessoaFisica>>() {
                }.getType());
        
        return CompPessoa;
    }

    @Override
    public CompetenciaPessoaFisica getCompetenciaPessoaFisicaCod(long CPF, int Cod_Competencia) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Cod_Competencia));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_CompetenciaPessoaFisica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        CompetenciaPessoaFisica CompPessoa = gson.fromJson(pacoteRecebido.getDados().get(0), CompetenciaPessoaFisica.class);
        return CompPessoa;
    }
    
}
