/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.VagaManagement;
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
 * @author Gabriel
 */
public class VagaSocketProxy implements  VagaManagement {
    
    private String serverAddress;
    private int serverPort;
    
    public VagaSocketProxy(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
       
    public VagaSocketProxy() {
        this("localhost", 2223);
    }
    
    @Override
    public void insert(Vaga Vaga) throws BusinessException, PersistenceException {
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("insert(Vaga Vaga)");
            writer.writeObject(Vaga);           
            writer.flush();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(VagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean update(long CNPJ, int Cod_Cargo, Date Dat_Publicacao, Vaga Vaga) throws BusinessException, PersistenceException {
        boolean result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("update(CNPJ, Cod_Cargo, Dat_Publicacao, Vaga)");
            writer.writeLong(CNPJ);
            writer.writeInt(Cod_Cargo);
            writer.writeObject((Date) Dat_Publicacao);
            writer.writeObject(Vaga);           
            writer.flush();
            
            result = reader.readBoolean();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(VagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public boolean delete(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        boolean result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("delete(CNPJ, Cod_Cargo, Dat_Publicacao, Vaga)");
            writer.writeLong(CNPJ);
            writer.writeInt(Cod_Cargo);
            writer.writeObject((Date) Dat_Publicacao);      
            writer.flush();
            
            result = reader.readBoolean();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(VagaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public List<Vaga> getVagaCNPJ(long CNPJ) throws PersistenceException {
        return null;    
    }

    @Override
    public List<Vaga> getVagaCod_Cargo(List<CargoInteresse> CarInteresse) throws PersistenceException {
        return null;
    }

    @Override
    public Vaga getVagaCod(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        return null;
    }
    
}
