/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        
        //Creación de instancias
        Barberia unaBarberia = new Barberia(/*cant de sillas*/ 5);
        Barbero unBarbero = new Barbero(unaBarberia);
        Cliente[] colClientes = new Cliente[10];
        for (int i = 0; i < colClientes.length; i++) {
            colClientes[i] = new Cliente(unaBarberia);
        }
        
        //Creación de hilos
        Thread hiloBarbero = new Thread(unBarbero, "Barbero");
        Thread[] hilosClientes = new Thread[colClientes.length];
        for (int i = 0; i < hilosClientes.length; i++) {
            hilosClientes[i] = new Thread(colClientes[i], "Cliente "+i);
        }
        
        //Start de hilos
        hiloBarbero.start();
        for (int i = 0; i < hilosClientes.length; i++) {
            hilosClientes[i].start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
