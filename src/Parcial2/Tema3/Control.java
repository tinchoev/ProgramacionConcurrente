/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Control {
    private Semaphore semSubir;
    private Semaphore semAsiento;
    private Semaphore semRecorrer;
    private Semaphore semBajar;
    private Semaphore mutex;
    private boolean montañaAbierta;
    private int capacidadPasajeros;
    private int cantPasajeros;
    
    public Control(int unaCantidad) {
        semSubir = new Semaphore(1);
        semAsiento = new Semaphore(unaCantidad);
        semRecorrer = new Semaphore(0);
        semBajar = new Semaphore(0);
        mutex = new Semaphore(1);
        montañaAbierta = true;
        capacidadPasajeros = unaCantidad;
        cantPasajeros = 0;
    }
    
    //Lógica pasajero
    public void subir() {
        try {
            semSubir.acquire();
            semAsiento.acquire();
            System.out.println(Thread.currentThread().getName()+": Sube a la montaña rusa");
            cantPasajeros++;
            if (cantPasajeros == capacidadPasajeros) {
                semRecorrer.release();
            }
            semSubir.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void bajar() {
        try {
            semBajar.acquire();
            mutex.acquire();
            System.out.println(Thread.currentThread().getName()+": Baja de la montaña rusa");
            cantPasajeros--;
            if (cantPasajeros == 0 && montañaAbierta) {
                semAsiento.release(capacidadPasajeros);
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Lógica montaña rusa
    public void recorrer() {
        try {
            semRecorrer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void terminar() {
        semBajar.release(capacidadPasajeros);
    }
    
    public void cerrar() {
        terminar();
        montañaAbierta = false;
    }
}
