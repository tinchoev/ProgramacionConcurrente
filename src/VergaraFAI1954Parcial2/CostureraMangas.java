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
public class CostureraMangas implements Runnable {
    private Taller miTaller;

    public CostureraMangas(Taller miTaller) {
        this.miTaller = miTaller;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(550);
                miTaller.crearManga();
            } catch (InterruptedException ex) {
                Logger.getLogger(CostureraMangas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
