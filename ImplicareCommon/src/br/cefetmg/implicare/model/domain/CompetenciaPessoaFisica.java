/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.model.domain;

/**
 *
 * @author Gabriel
 */
public class CompetenciaPessoaFisica {
    private long CPF;
    private int Cod_Competencia;
    private int Cod_Proficiencia;
    
    public CompetenciaPessoaFisica(){}
    
    public CompetenciaPessoaFisica(long CPF, int Cod_Competencia, int Cod_Proficiencia){
        this.CPF = CPF;
        this.Cod_Competencia = Cod_Competencia;
        this.Cod_Proficiencia = Cod_Proficiencia;
    }
    
    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public int getCod_Competencia() {
        return Cod_Competencia;
    }

    public void setCod_Competencia(int Cod_Competencia) {
        this.Cod_Competencia = Cod_Competencia;
    }

    public int getCod_Proficiencia() {
        return Cod_Proficiencia;
    }

    public void setCod_Proficiencia(int Cod_Proficiencia) {
        this.Cod_Proficiencia = Cod_Proficiencia;
    }
    
}
