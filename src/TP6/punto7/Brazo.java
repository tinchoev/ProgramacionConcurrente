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
public class Brazo implements Runnable {
    private Mostrador miMostrador;
    
    public Brazo (Mostrador unMostrador) {
        miMostrador = unMostrador;
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep((long) (Math.random()+1)*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println(Thread.currentThread().getName()+": intento retirar caja");
                miMostrador.retirarCaja();
                System.out.println(Thread.currentThread().getName()+": intento reponer");
                miMostrador.reponerCaja();
                System.out.println(Thread.currentThread().getName()+": repuse caja");
            }
        }
    }
}
