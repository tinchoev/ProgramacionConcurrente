/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_FAI1954_recuperatorio;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Cordero implements Runnable {
    private Rio miRio;
    
    public Cordero(Rio unRio) {
        miRio = unRio;
    }
    
    public void run() {
        Random r = new Random();
        while (true) {
            try {
                //Espera a que le dé sed
                Thread.sleep(r.nextInt(5)*3000);
                miRio.entrar();
                //Simula acción de beber
                Thread.sleep((int) (Math.random()+2)*2000);
                miRio.salir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Lobo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
