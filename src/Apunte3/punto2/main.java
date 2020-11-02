/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {

    public static void main(String[] args) {
        
        //Creación instancias
        Plato unPlato = new Plato(3);
        Rueda unaRueda = new Rueda();
        Jaula unaJaula = new Jaula(unPlato, unaRueda);
        HamsterMonitor[] colHamsters = new HamsterMonitor[7];
        for (int i = 0; i < colHamsters.length; i++) {
            colHamsters[i] = new HamsterMonitor(unaJaula, "Hamster " + i);
        }
        
        //Creación hilos
        Thread[] hilosHamsters = new Thread[colHamsters.length];
        for (int i = 0; i < hilosHamsters.length; i++) {
            hilosHamsters[i] = new Thread(colHamsters[i]);
        }
        
        //Start hilos
        for (int i = 0; i < hilosHamsters.length; i++) {
            hilosHamsters[i].start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
