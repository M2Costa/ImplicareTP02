/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Usuario;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.UsuarioManagement;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class UsuarioSocketProxy implements UsuarioManagement{
    
    private String serverAddress;
    private int serverPort;
    
    UsuarioSocketProxy(String serverAddress, int serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;   
    }
    
    UsuarioSocketProxy(){
        this("localhost", 2223);
    }
    
    @Override
    public void insert(Usuario Usuario) throws BusinessException, PersistenceException {
       Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("insert(Usuario Usuario)");
            writer.writeObject(Usuario);           
            writer.flush();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean update(Long CPF_CNPJ, Usuario Usuario) throws BusinessException, PersistenceException {
       boolean result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("update(CPF_CNPJ, Usuario)");
            writer.writeLong(CPF_CNPJ);
            writer.writeObject(Usuario);           
            writer.flush();
            
            result = reader.readBoolean();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public Usuario getUsuarioCod(Long CPF_CNPJ) throws PersistenceException {
        Usuario Usuario; 
        Usuario = new Usuario();
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("GetUsuarioCod(CPF_CNPJ)");
            writer.writeLong(CPF_CNPJ);         
            writer.flush();
            
    
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return Usuario;
    }

    @Override
    public Usuario getLogin(Long CPF_CNPJ, String Senha) throws PersistenceException {
         Usuario Usuario; 
        Usuario = new Usuario();
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("GetLogin(CPF_CNPJ, Senha)");
            writer.writeLong(CPF_CNPJ);
            writer.writeUTF(Senha);
            writer.flush();
            
    
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return Usuario;
    }

    
}
