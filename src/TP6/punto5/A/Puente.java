/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto5.A;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Puente {
    private Semaphore semPuente;
    private char poseedor;
    
    public Puente(int capacidadPuente) {
        semPuente = new Semaphore(capacidadPuente);
        poseedor = ' ';
    }
    
    public void entrarAlPuente(char direccion) {
        if (direccion == poseedor) {
            try {
                semPuente.acquire();
                System.out.println(Thread.currentThread().getName()+": entró al puente por el "+direccion);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void salirDelPuente() {
        semPuente.release();
        System.out.println(Thread.currentThread().getName()+": salió del puente");
    }
}
