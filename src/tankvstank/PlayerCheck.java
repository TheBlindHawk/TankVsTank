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
 * @author user
 */
public class PlayerCheck implements Runnable{
    Player p;
    Semaphore control;
    ArrayList <Sparo>bullet;
    Vita life;
    PlayerCheck(Player p,ArrayList<Sparo> bullet,Semaphore control,Vita life){
        this.p=p;
        this.bullet=bullet;
        this.control=control;
        this.life=life;
    }
    public void check(){
        if(!bullet.isEmpty()){
            for(int i=0;i<bullet.size();i++){
                if(p.getPlayer().intersects(bullet.get(i).getPallottola())){
                    bullet.remove(i);
                    if(life.getPunti()!=0){
                        life.diminuisci();
                        life.caricaVettore();
                        life.Caricamento();
                    }
                }
            }
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
