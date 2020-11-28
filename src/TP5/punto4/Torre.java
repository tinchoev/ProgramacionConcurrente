/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.punto4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Torre {
    private Semaphore semPista;
    private Semaphore semPrioridadAterrizaje;
    private int nivelPrioridad;
    
    public Torre(int unNivelPrioridad) {
        semPista = new Semaphore(1);
        semPrioridadAterrizaje = new Semaphore(10);
        nivelPrioridad = unNivelPrioridad;
    }
    
    public void aterrizar(int miPrioridad) {
        //try {
            System.out.println(Thread.currentThread().getName()+": intentando aterrizar");
            verificarPrioridad(miPrioridad, "aterrizando");
        /*} catch (InterruptedException ex) {
            Logger.getLogger(Torre.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void despegar(int miPrioridad) {
        System.out.println(Thread.currentThread().getName()+": intentando despegar");
        verificarPrioridad(miPrioridad, "despegando");        
    }
    
    private void utilizarPista(String cadena1) {
        try {
            System.out.println(Thread.currentThread().getName()+": "+cadena1);
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+": pista liberada");
            semPista.release();
            actualizarPrioridad();
        } catch (InterruptedException ex) {
            Logger.getLogger(Torre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificarPrioridad(int miPrioridad, String cadena) {
        if (miPrioridad==nivelPrioridad) {
            System.out.println(Thread.currentThread().getName()+": tengo prioridad");
            try {
                semPista.acquire();
                utilizarPista(cadena);
            } catch (InterruptedException ex) {
                Logger.getLogger(Torre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println(Thread.currentThread().getName()+": no tengo prioridad, así que espero");
            esperar(miPrioridad, cadena);
        }
    }
    
    private void esperar(int miPrioridad, String cadena) {
        try {
            Thread.sleep(3000);
            verificarPrioridad(miPrioridad, cadena);
        } catch (InterruptedException ex) {
            Logger.getLogger(Torre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarPrioridad() {
        try {
            semPrioridadAterrizaje.acquire();
            if (semPrioridadAterrizaje.availablePermits()==0) {
                nivelPrioridad = 2;
                semPrioridadAterrizaje.release(10);
            } else {
                if (nivelPrioridad != 1) {
                    nivelPrioridad = 1;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Torre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
