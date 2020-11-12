/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación instancias
        GestorSala unaSala = new GestorSala(30);
        ControlTemperatura unTermo = new ControlTemperatura(unaSala);
        Persona[] colPersonas = new Persona[15];
        boolean unBoolean;
        for (int i = 0; i < colPersonas.length; i++) {
            if (i%2 == 0) {
                unBoolean = true;
            } else {
                unBoolean = false;
            }
            colPersonas[i] = new Persona(unaSala, unBoolean);
        }
        //Creación hilos
        Thread hiloTermo = new Thread(unTermo, "Termómetro");
        Thread[] hilosPersonas = new Thread[colPersonas.length];
        for (int i = 0; i < hilosPersonas.length; i++) {
            hilosPersonas[i] = new Thread(colPersonas[i], "Persona "+i);
        }
        //Start hilos
        hiloTermo.start();
        for (int i = 0; i < hilosPersonas.length; i++) {
            hilosPersonas[i].start();
            /*try {
                Thread.sleep(550);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
}
