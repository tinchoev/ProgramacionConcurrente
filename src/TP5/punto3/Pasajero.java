/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Pasajero implements Runnable{
    private ControlTren control;
    
    public Pasajero(ControlTren unControl) {
        control = unControl;
    }
    
    public void run() {
        try {
            control.comprarTicket();
            Thread.sleep(1000);
            control.subirTren();
            control.bajarTren();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
