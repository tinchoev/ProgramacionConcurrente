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
public class Animal implements Runnable{
    private Comedor miComedor;
    private int nivelPrioridad;
    
    public Animal(Comedor unComedor, int unaPrioridad) {
        miComedor = unComedor;
        nivelPrioridad = unaPrioridad;
    }
    
    public void run() {
        miComedor.entrarComedor(nivelPrioridad);
        comer();
        miComedor.salirComedor();
    }
    
    private void comer() {
        System.out.println(Thread.currentThread().getName()+": Estoy comiendo");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Animal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
