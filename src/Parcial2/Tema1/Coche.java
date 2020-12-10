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
public class Coche implements Runnable {
    private Control miControl;
    
    public Coche(Control unControl) {
        miControl = unControl;
    }
    
    public void run() {
        simularAccion();//Simula viaje al transbordador
        //System.out.println(Thread.currentThread().getName()+": listo para subir");
        miControl.subir();
        miControl.bajar();
    }
    
    private void simularAccion() {
        try {
            Thread.sleep((long) (Math.random()+1)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
