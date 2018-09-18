/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.CandidatoVaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CandidatoVagaManagement;
import br.cefetmg.implicare.model.serviceImpl.CandidatoVagaManagementImpl;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morato
 */
public class CandidatoVagaSocketAdapter implements Runnable {
    
    private final Socket Socket;
    private final CandidatoVagaManagement CandidatoVagaMan;
    
    public CandidatoVagaSocketAdapter(Socket Socket) {
        this.Socket = Socket;
        this.CandidatoVagaMan = new CandidatoVagaManagementImpl();
    }
    
    private Socket getSocket(){
        return Socket;
    }

    

    @Override
    public void run() {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());            
            
            String command;
            
            command = reader.readUTF();
        switch(command) {
                case "insert(CandidatoVaga)":
                    CandidatoVaga CandidatoVaga = (CandidatoVaga) reader.readObject();            
                    CandidatoVagaMan.insert(CandidatoVaga);
                    break;                
                    
                case "update(CPF, Cod_Cargo, CNPJ, Dat_Publicacao)":
                    Long CPF = reader.readLong();
                    int Cod_Cargo = reader.readInt(); 
                    Long CNPJ = reader.readLong();
                    Date Dat_Publicacao = (Date) reader.readObject();
                    CandidatoVaga = (CandidatoVaga) reader.readObject();
                    boolean result = CandidatoVagaMan.update(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, CandidatoVaga);
                    writer.writeBoolean(result);
                    break;
                 
                case "getCandidatosVaga(Cod_Cargo, CNPJ, Dat_Publicacao)": 
                    Cod_Cargo = reader.readInt();
                    CNPJ = reader.readLong();
                    Dat_Publicacao = (Date) reader.readObject();
                    List<CandidatoVaga> resultado = CandidatoVagaMan.getCandidatosVaga(Cod_Cargo, CNPJ, Dat_Publicacao);
                    writer.writeObject(resultado);
                    break;
                    
                case "getCandidatoVagaCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao)":
                    CPF = reader.readLong();
                    Cod_Cargo = reader.readInt();
                    CNPJ = reader.readLong();
                    Dat_Publicacao = (Date) reader.readObject();
                    CandidatoVaga resultado2 = CandidatoVagaMan.getCandidatoVagaCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao);
                    writer.writeObject(resultado2);
                    break;   
            }
            
            writer.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (BusinessException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenceException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
