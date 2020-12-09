/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class CostureraCuerpo implements Runnable {
    private Taller miTaller;

    public CostureraCuerpo(Taller miTaller) {
        this.miTaller = miTaller;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random()+1)*2000);
                miTaller.crearCuerpo();
            } catch (InterruptedException ex) {
                Logger.getLogger(CostureraCuerpo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
