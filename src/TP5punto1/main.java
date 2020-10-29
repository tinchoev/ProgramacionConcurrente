/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación de instancias
        Comedor elComedor = new Comedor(/*capacidad*/ 2, /*prioridad*/ 1);
        Gato[] colGatos = new Gato[2];
        for (int i = 0; i < colGatos.length; i++) {
            colGatos[i] = new Gato(elComedor, /*nivel de prioridad*/ 1);
        }
        Perro[] colPerros = new Perro[2];
        for (int i = 0; i < colPerros.length; i++) {
            colPerros[i] = new Perro(elComedor, /*nivel de prioridad*/ 2);
        }
        //Creación de hilos
        Thread[] hilosGatos = new Thread[colGatos.length];
        for (int i = 0; i < hilosGatos.length; i++) {
            hilosGatos[i] = new Thread(colGatos[i], "Gato-"+i);
        }
        Thread[] hilosPerros = new Thread[colPerros.length];
        for (int i = 0; i < hilosPerros.length; i++) {
            hilosPerros[i] = new Thread(colPerros[i], "Perro-"+i);
        }
        //Start de hilos
        for (int i = 0; i < hilosGatos.length; i++) {
            hilosGatos[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < hilosPerros.length; i++) {
            hilosPerros[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
