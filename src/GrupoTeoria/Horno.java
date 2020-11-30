/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrupoTeoria;

import GrupoTeoria.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Horno implements Runnable {
    private Mostrador miMostrador;
    private int pesoPastel;
    
    public Horno (Mostrador unMostrador, int unPeso) {
        miMostrador = unMostrador;
        pesoPastel = unPeso;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((long) (Math.random()+1)*2000);
            }  catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                //System.out.println(Thread.currentThread().getName()+": cocinando pastel de "+pesoPastel);
                miMostrador.cocinarPastel(pesoPastel);
            }
        }
    }
}
