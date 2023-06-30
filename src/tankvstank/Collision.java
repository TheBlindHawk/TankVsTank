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
 * @author studente
 */
public class Collision implements Runnable
{
    int i,n=5,t=10,N=30;
    int del=3;
    private volatile Player p1,p2;
    Collision(Player p1,Player p2){
        this.p1=p1;
        this.p2=p2;
    }
    
    @Override
    public void run(){
        while(true){
            if(p1.getPlayer()!=null&&p2.getPlayer()!=null)
                if(p1.getPlayer().intersects(p2.getPlayer()))
                    if(p1.getX()<=p2.getX())
                        if(p1.getY()<=p2.getY())
                            if((p2.getX()-p1.getX())<=(p2.getY()-p1.getY())){
                                for(int i=0;i<N;i++){
                                    if(p1.getY()>0)
                                        p1.U(n);
                                    if(p2.getY()<TankVsTank.H-130)
                                        p2.D(n);
                                }
                            }
                            else{
                                for(int i=0;i<N;i++){
                                    if(p1.getX()>0)
                                        p1.L(n);
                                    if(p2.getX()<TankVsTank.L-85)
                                        p2.R(n);
                                }
                            }
                        else{
                            if((p2.getX()-p1.getX())<=(p1.getY()-p2.getY())){
                                for(int i=0;i<N;i++){
                                    if(p1.getY()<TankVsTank.H-130)
                                        p1.D(n);
                                    if(p2.getY()>0)
                                        p2.U(n);
                                }
                            }
                            else{
                                for(int i=0;i<N;i++){
                                    if(p1.getX()>0)
                                        p1.L(n);
                                    if(p2.getX()<TankVsTank.L-85)
                                        p2.R(n);
                                }
                            }
                        }
                    else
                        if(p1.getY()<=p2.getY())
                            if((p1.getX()-p2.getX())<(p2.getY()-p1.getY())){
                                for(int i=0;i<N;i++){
                                    if(p1.getY()>0)
                                        p1.U(n);
                                    if(p2.getY()<TankVsTank.H-130)
                                        p2.D(n);
                                }
                            }
                            else{
                                for(int i=0;i<N;i++){
                                    if(p1.getX()<TankVsTank.L-130)
                                        p1.R(n);
                                    if(p2.getX()>0)
                                        p2.L(n);
                                }
                            }
                        else{
                            if((p1.getX()-p2.getX())<(p1.getY()-p2.getY())){
                                for(int i=0;i<N;i++){
                                    if(p1.getY()<TankVsTank.L-85)
                                        p1.D(n);
                                    if(p2.getY()>0)
                                        p2.U(n);
                                }
                            }
                            else{
                                for(int i=0;i<N;i++){
                                    if(p1.getX()<TankVsTank.L-130)
                                        p1.R(n);
                                    if(p2.getX()>0)
                                        p2.L(n);
                                }
                            }
                        }
            aspetta(1);
        }
    }
    public void aspetta(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
