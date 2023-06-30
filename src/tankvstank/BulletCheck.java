/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Megaport
 */
public class BulletCheck implements Runnable{
    Semaphore control;
    ArrayList <Sparo>bullet;
    double nx,ny;
    BulletCheck(ArrayList <Sparo>bullet, Semaphore control){
        this.bullet=bullet;
        this.control=control;
    }
    public void check(){
        if(!bullet.isEmpty())
            for(int i=0;i<bullet.size();i++){
                nx=bullet.get(i).getX();
                ny=bullet.get(i).getY();
                if(nx<0||nx>TankVsTank.L-85||ny<0||ny>TankVsTank.H-130)
                    bullet.remove(i);
            }
            
    }
    @Override
    public void run() {
        while(true){
            try {
                control.acquire();
                check();
                control.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
