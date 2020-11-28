/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Avion implements Runnable {
    private Torre miTorre;
    
    public Avion(Torre unaTorre) {
        miTorre = unaTorre;
    }
    
    public void run() {
        while (true) {
            miTorre.aterrizar(1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }
            miTorre.despegar(2);
        }
    }
}
