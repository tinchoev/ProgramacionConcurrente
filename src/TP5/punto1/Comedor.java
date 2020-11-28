/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.punto1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Comedor {
    private int capacidad;
    private Semaphore semCapacidad;
    private int nivelPrioridad;
    private int cantComidos = 0;
    
    public Comedor(int unaCapacidad, int unaPrioridad) {
        capacidad = unaCapacidad;
        semCapacidad = new Semaphore(capacidad);
        nivelPrioridad = unaPrioridad;
    }
    
    public void entrarComedor(int prioridad) {
        System.out.println(Thread.currentThread().getName()+": Intentando entrar al comedor");
        if (nivelPrioridad==prioridad && semCapacidad.tryAcquire()) {
            System.out.println(Thread.currentThread().getName()+": entré");
            cantComidos++;
            cambiarPrioridad();
        } else {
            System.out.println(Thread.currentThread().getName()+": espero");
            esperar(prioridad);
        }
    }
    
    private void esperar(int prioridad) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrarComedor(prioridad);
        }
    }
    
    public void salirComedor() {
        System.out.println(Thread.currentThread().getName()+": Salgo del comedor");
        semCapacidad.release();
    }
    
    private void cambiarPrioridad() {
        if (cantComidos==capacidad) {
            System.out.println("Se cambia la prioridad de especie");
            if (nivelPrioridad==1) {
                nivelPrioridad = 2;
            } else {
                nivelPrioridad = 1;
            }
            cantComidos = 0;
        }
    }
}
