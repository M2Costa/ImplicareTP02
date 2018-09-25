/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.FormacaoAcademicaManagement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */

public class FormacaoAcademicaSocketProxy implements FormacaoAcademicaManagement {
    
    Cliente Cliente;
    
    public FormacaoAcademicaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(FormacaoAcademica FormacaoAcademica) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(FormacaoAcademica));
        pacoteEnviado = new Pacote(TipoOperacao.INS_FormacaoAcademica, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(long CPF, int Seq_Formacao, int Cod_Area_Estudo, FormacaoAcademica FormacaoAcademica) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Formacao));
        dados.add(gson.toJson(Cod_Area_Estudo));
        dados.add(gson.toJson(FormacaoAcademica));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_FormacaoAcademica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public boolean delete(long CPF, int Seq_Formacao, int Cod_Area_Estudo) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Formacao));
        dados.add(gson.toJson(Cod_Area_Estudo));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_FormacaoAcademica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<FormacaoAcademica> getFormacaoAcademica(long CPF) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_FormacaoAcademica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<FormacaoAcademica> FormAcad = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<FormacaoAcademica>>() {
                }.getType());
        
        return FormAcad;
    }

    @Override
    public FormacaoAcademica getFormacaoAcademicaCod(long CPF, int Seq_Formacao, int Cod_Area_Estudo) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Seq_Formacao));
        dados.add(gson.toJson(Cod_Area_Estudo));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_FormacaoAcademica, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        FormacaoAcademica FormAcad = gson.fromJson(pacoteRecebido.getDados().get(0), FormacaoAcademica.class);
        return FormAcad;
    }
    
}
