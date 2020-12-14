/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_FAI1954_recuperatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Lobo implements Runnable {
    private Rio miRio;
    
    public Lobo(Rio unRio) {
        miRio = unRio;
    }
    
    public void run() {
        while (true) {
            try {
                //Espera a que le dé sed
                Thread.sleep((int) (Math.random()+2)*5000);
                System.out.println(Thread.currentThread().getName()+": Voy a beber");
                miRio.beber();
                //Simula acción de beber
                Thread.sleep((int) (Math.random()+2)*2000);
                System.out.println(Thread.currentThread().getName()+": Terminé de beber");
            } catch (InterruptedException ex) {
                Logger.getLogger(Lobo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
