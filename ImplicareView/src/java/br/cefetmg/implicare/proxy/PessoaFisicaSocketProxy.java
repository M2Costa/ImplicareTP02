/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.PessoaFisicaManagement;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */

public class PessoaFisicaSocketProxy implements PessoaFisicaManagement {
    
    Cliente Cliente;
    
    public PessoaFisicaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(PessoaFisica PessoaFisica) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(PessoaFisica));
        pacoteEnviado = new Pacote(TipoOperacao.INS_PessoaFisica, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(Long CPF, PessoaFisica PessoaFisica) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(PessoaFisica));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_PessoaFisica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public PessoaFisica getPessoaFisicaCod(Long CPF) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_PessoaFisica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        PessoaFisica Pessoa = gson.fromJson(pacoteRecebido.getDados().get(0), PessoaFisica.class);
        return Pessoa;
    }
    
}
