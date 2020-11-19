/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto7;

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
                Thread.sleep((long) (Math.random()+1)*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println(Thread.currentThread().getName()+": Intento tomar pastel");
                peso = miMostrador.tomarPastel();
                System.out.println(Thread.currentThread().getName()+": Tomé pastel, intento soltarlo");
                miMostrador.soltarPastel(peso);
                //System.out.println(Thread.currentThread().getName()+": solté pastel");
            }
        }
    }
}
