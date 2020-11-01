/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación de instancias
        Torre unaTorre = new Torre(/*prioridad*/ 1);
        Avion[] colAviones = new Avion[20];
        for (int i = 0; i < 10; i++) {
            colAviones[i] = new Avion(unaTorre);
        }
        
        //Creación de hilos
        Thread[] hilosAviones = new Thread[colAviones.length];
        for (int i = 0; i < hilosAviones.length; i++) {
            hilosAviones[i] = new Thread(colAviones[i]);
        }
        
        //Start de hilos
        for (int i = 0; i < hilosAviones.length; i++) {
            hilosAviones[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
