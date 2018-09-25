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
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CompetenciaPessoaFisicaSocketProxy implements CompetenciaPessoaFisicaManagement {
    
    Cliente Cliente;
    
    public CompetenciaPessoaFisicaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
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
