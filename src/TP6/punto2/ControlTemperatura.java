/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class ControlTemperatura implements Runnable {
    private GestorSala miSala;
    
    public ControlTemperatura(GestorSala unaSala) {
        miSala = unaSala;
    }
    
    public void run() {
        while (true) {
            try {
                miSala.notificarTemperatura(35);
                Thread.sleep(5000);
                miSala.notificarTemperatura(25);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlTemperatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
