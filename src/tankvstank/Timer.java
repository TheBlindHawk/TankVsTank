/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Timer extends Thread{
    private volatile boolean permesso=true;
    Timer(){
        this.start();
    }
    public void setPermesso(boolean permesso){
        this.permesso=permesso;
    }
    public void aspetta(int millisec){
        try {
            this.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean getPermesso(){
        return permesso;
    }
    public void run(){
	while(true){
            if(!permesso){
                aspetta(300);
                permesso=true;
            }
	}
    }
    
}
