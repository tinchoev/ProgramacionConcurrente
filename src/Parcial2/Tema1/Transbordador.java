/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Transbordador implements Runnable {
    private Control miControl;
    
    public Transbordador(Control unControl) {
        miControl = unControl;
    }
    
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName()+": Esperando autos");
            miControl.ir();
            viajar();
            miControl.volver();
        }
    }
    
    private void viajar() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
