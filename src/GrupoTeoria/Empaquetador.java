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
public class Empaquetador implements Runnable {
    private Mostrador miMostrador;
    
    public Empaquetador (Mostrador unMostrador) {
        miMostrador = unMostrador;
    }
    public void run() {
        int peso;
        while(true) {
            try {
                Thread.sleep((long) (Math.random()+1)*2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                peso = miMostrador.tomarPastel();
                //System.out.println(Thread.currentThread().getName()+": Tomé pastel de "+peso+", intento soltarlo");
                miMostrador.soltarPastel(peso);
            }
        }
    }
}
