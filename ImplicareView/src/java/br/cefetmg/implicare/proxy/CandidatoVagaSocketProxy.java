/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.CandidatoVaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CandidatoVagaManagement;
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
public class CandidatoVagaSocketProxy implements CandidatoVagaManagement {
    
    private String serverAddress;
    private int serverPort;
    
    public CandidatoVagaSocketProxy(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
       
    public CandidatoVagaSocketProxy() {
        this("localhost", 2223);
    }
    
    @Override
    public void insert(br.cefetmg.implicare.model.domain.CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("insert(CandidatoVaga)");
            writer.writeObject(CandidatoVaga);           
            writer.flush();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean update(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao, br.cefetmg.implicare.model.domain.CandidatoVaga CandidatoVaga) throws BusinessException, PersistenceException {
        boolean result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("update(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, CandidatoVaga)");
            writer.writeLong(CPF);
            writer.writeInt(Cod_Cargo);
            writer.writeLong(CNPJ);
            writer.writeObject((Date) Dat_Publicacao);
            writer.writeObject(CandidatoVaga);
            writer.flush();
            
            result = reader.readBoolean();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public List<br.cefetmg.implicare.model.domain.CandidatoVaga> getCandidatosVaga(int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        List<CandidatoVaga> result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("getCandidatosVaga(Cod_Cargo, CNPJ, Dat_Publicacao)");
            writer.writeInt(Cod_Cargo);
            writer.writeLong(CNPJ);
            writer.writeObject((Date) Dat_Publicacao);          
            writer.flush();
            
            result = null;
            
            try {
                result = (List<CandidatoVaga>)reader.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public br.cefetmg.implicare.model.domain.CandidatoVaga getCandidatoVagaCod(long CPF, int Cod_Cargo, long CNPJ, Date Dat_Publicacao) throws PersistenceException {
        CandidatoVaga result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("getCandidatoVagaCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao)");
            writer.writeLong(CPF);
            writer.writeInt(Cod_Cargo);
            writer.writeLong(CNPJ);
            writer.writeObject((Date) Dat_Publicacao);          
            writer.flush();
            
            result = null;
            
            try {
                result = (CandidatoVaga) reader.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CandidatoVagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
 
    }
    
    
}
