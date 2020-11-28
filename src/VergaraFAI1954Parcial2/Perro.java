/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Perro implements Runnable{
    private Comedor miComedor;

    public Perro(Comedor miComedor) {
        this.miComedor = miComedor;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random()+1)*2000);//Simula la tardanza hasta el comedor
                miComedor.comer();
                Thread.sleep((long) (Math.random()+1)*3000);//Simula que come
                miComedor.terminarDeComer();
            } catch (InterruptedException ex) {
                Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
