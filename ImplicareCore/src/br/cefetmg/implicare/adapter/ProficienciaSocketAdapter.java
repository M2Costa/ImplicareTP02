/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.ProficienciaManagement;
import br.cefetmg.implicare.model.serviceImpl.ProficienciaManagementImpl;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ProficienciaSocketAdapter implements Runnable {

    
    private final Socket Socket;
    private final ProficienciaManagement ProfMan;
    
    
    private Socket getSocket(){
        return Socket;
    }

    
    
    public ProficienciaSocketAdapter(Socket socket) {
        this.Socket = socket;
        this.ProfMan = new ProficienciaManagementImpl();
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
                case "listAll()":
                    
                    List<Proficiencia> List = ProfMan.listAll();
                    writer.writeObject(List);
                    break;                
                    
                
                case "getProficienciaCod(Cod_Proficiencia)": 
                    int Cod_Proficiencia = reader.readInt();
                    Proficiencia Prof = ProfMan.getProficienciaCod(Cod_Proficiencia);
                    writer.writeObject(Prof);
                    break;
                    
                
            }
            
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ProficienciaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (PersistenceException ex) {
            Logger.getLogger(ProficienciaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
