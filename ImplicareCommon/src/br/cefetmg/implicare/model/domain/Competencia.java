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
public class Competencia {
    private int Cod_Competencia;
    private String Nom_Competencia;
    
    public Competencia(){}
    
    public Competencia(int Cod_Competencia,String Nom_Competencia){
        this.Cod_Competencia = Cod_Competencia;
        this.Nom_Competencia = Nom_Competencia;
    }

    public int getCod_Competencia() {
        return Cod_Competencia;
    }

    public void setCod_Competencia(int Cod_Competencia) {
        this.Cod_Competencia = Cod_Competencia;
    }

    public String getNom_Competencia() {
        return Nom_Competencia;
    }

    public void setNom_Competencia(String Nom_Competencia) {
        this.Nom_Competencia = Nom_Competencia;
    }
    
}
