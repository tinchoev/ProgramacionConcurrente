/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Generador implements Runnable{
    private Agua miAgua;
    
    public Generador(Agua unAgua) {
        miAgua = unAgua;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random()+2)*1000);
                miAgua.generar();
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
