/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Productor implements Runnable {
    private Buffer miBuffer;
    
    public Productor(Buffer unBuffer) {
        miBuffer = unBuffer;
    }
    
    public void run() {
        while (true) {
            miBuffer.producir();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
