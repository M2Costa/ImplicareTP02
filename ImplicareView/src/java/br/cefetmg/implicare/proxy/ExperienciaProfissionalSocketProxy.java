/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.ExperienciaProfissional;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ExperienciaProfissionalManagement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */

public class ExperienciaProfissionalSocketProxy implements ExperienciaProfissionalManagement {
    
    Cliente Cliente;
    
    public ExperienciaProfissionalSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(ExperienciaProfissional ExperienciaProfissional) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(ExperienciaProfissional));
        pacoteEnviado = new Pacote(TipoOperacao.INS_ExperienciaProfissional, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(Long CPF, int Seq_Experiencia, int Cod_Cargo, ExperienciaProfissional ExperienciaProfissional) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Experiencia));
        dados.add(gson.toJson(Cod_Cargo));
        dados.add(gson.toJson(ExperienciaProfissional));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_ExperienciaProfissional, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public boolean delete(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Experiencia));
        dados.add(gson.toJson(Cod_Cargo));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_ExperienciaProfissional, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<ExperienciaProfissional> getExperienciasProfissionais(Long CPF) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_ExperienciaProfissional, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<ExperienciaProfissional> ExpProfissional = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<ExperienciaProfissional>>() {
                }.getType());
        
        return ExpProfissional;
    }

    @Override
    public ExperienciaProfissional getExperienciaProfissionalCod(Long CPF, int Seq_Experiencia, int Cod_Cargo) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Experiencia));
        dados.add(gson.toJson(Cod_Cargo));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_ExperienciaProfissional, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        ExperienciaProfissional ExpProfissional = gson.fromJson(pacoteRecebido.getDados().get(0), ExperienciaProfissional.class);
        return ExpProfissional;
    }
    
}
