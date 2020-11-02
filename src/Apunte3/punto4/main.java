/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {

    public static void main(String[] args) {

        //Creación de instancias
        Buffer unBuffer = new Buffer(3);
        Productor[] productores = new Productor[5];
        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Productor(unBuffer);
        }
        Consumidor[] consumidores = new Consumidor[5];
        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor(unBuffer);
        }

        //Creación de hilos
        Thread[] hiloProductor = new Thread[5];
        for (int i = 0; i < hiloProductor.length; i++) {
            hiloProductor[i] = new Thread(productores[i], "Productor " + i);
        }
        Thread[] hiloConsumidor = new Thread[5];
        for (int i = 0; i < hiloConsumidor.length; i++) {
            hiloConsumidor[i] = new Thread(consumidores[i], "Consumidor " + i);
        }

        //Start de hilos
        for (int i = 0; i < hiloProductor.length; i++) {
            hiloProductor[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < hiloConsumidor.length; i++) {
            hiloConsumidor[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
