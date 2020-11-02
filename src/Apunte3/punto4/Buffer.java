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
public class Buffer {
    
    private int cantDatos;
    private int cantMaxDatos;

    //Constructor
    public Buffer(int cantMaxDatos) {    
        this.cantDatos = 0;
        this.cantMaxDatos = cantMaxDatos;
    }

    //Parte Consumidor
    public synchronized void consumir() {
        try {
            while (cantDatos <= 0) {
                System.out.println(Thread.currentThread().getName() + " no tiene datos que consumir");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" consumió un dato");
        cantDatos--;
        this.notify();
    }

    //Parte Productor
    public synchronized void producir() {
        try {
            while (cantMaxDatos <= cantDatos) {
                System.out.println(Thread.currentThread().getName() + " límite de datos existentes alcanzado");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" produjo un dato");
        cantDatos++;
        this.notify();
    }
}
