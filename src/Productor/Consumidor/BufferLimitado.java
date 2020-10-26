/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productor.Consumidor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class BufferLimitado {
    private Semaphore datos = new Semaphore(0);
    private Semaphore prod = new Semaphore(3);
    
    public BufferLimitado() {
        
    }
    
    public void consumir() {
        try {
            datos.acquire();
            System.out.println("Consumiendo");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BufferLimitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prod.release();
        }
    }
    
    public void producir() {
        try {
            prod.acquire();
            System.out.println("Produciendo");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BufferLimitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            datos.release();
        }
    }
}
