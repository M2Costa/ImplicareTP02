/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.Usuario;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.UsuarioManagement;
import br.cefetmg.implicare.model.service.VagaManagement;
import br.cefetmg.implicare.model.serviceImpl.UsuarioManagementImpl;
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
public class UsuarioSocketAdapter implements Runnable {
    
    private final Socket Socket;
    private final UsuarioManagement UsrMan;
    
    public UsuarioSocketAdapter(Socket socket) {
        this.Socket = socket;
        this.UsrMan = new UsuarioManagementImpl();
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
                case "insert(Usuario)":
                    Usuario Usuario = (Usuario) reader.readObject();            
                    UsrMan.insert(Usuario);
                    break;                
                    
                case "update(CPF_CNPJ, Usuario)":
                    Long CPF_CNPJ = reader.readLong();
                    
                    Usuario = (Usuario) reader.readObject();
                    boolean result = UsrMan.update(CPF_CNPJ, Usuario);
                    writer.writeBoolean(result);
                    break;
                    
                case "getUsuarioCod(CPF_CNPJ)": 
                    CPF_CNPJ = reader.readLong();
                    Usuario usr = UsrMan.getUsuarioCod(CPF_CNPJ);
                    writer.writeObject(usr);
                    break;
                    
                case "getLogin(CPF_CNPJ, Senha)":
                    CPF_CNPJ = reader.readLong();
                    String Senha = (String) reader.readObject();
                    usr = UsrMan.getLogin(CPF_CNPJ, Senha);
                    writer.writeObject(usr);
                    break;
            }
            
            writer.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (BusinessException ex) {
            Logger.getLogger(UsuarioSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenceException ex) {
            Logger.getLogger(UsuarioSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
