/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ProficienciaManagement;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ProficienciaSocketProxy implements ProficienciaManagement {
    
    private String serverAddress;
    private int serverPort;
    
    ProficienciaSocketProxy(String serverAddress, int serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;   
    }
    
    ProficienciaSocketProxy(){
        this("localhost", 2223);
    }
    
    @Override
    public List<Proficiencia> listAll() throws PersistenceException {
        List<Proficiencia> List;
        List = new ArrayList();
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("listAll()");         
            writer.flush();
            
    
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProficienciaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return List;
    }

    @Override
    public Proficiencia getProficienciaCod(int Cod_Proficiencia) throws PersistenceException {
        Proficiencia Proficiencia; 
        Proficiencia = new Proficiencia();
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("GetProficienciaCod(Cod_Proficiencia)");
            writer.writeInt(Cod_Proficiencia);         
            writer.flush();
            
    
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProficienciaSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return Proficiencia;
    }
    
}
