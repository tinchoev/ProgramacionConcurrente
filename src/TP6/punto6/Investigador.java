/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Investigador implements Runnable {
    private Observatorio miObservatorio;
    
    public Investigador(Observatorio unObservatorio) {
        miObservatorio = unObservatorio;
    }
    
    private void investigar() {
        try {
            Thread.sleep((long) (Math.random()+1)*3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run() {
        try {
            Thread.sleep((long) (Math.random()+1)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+": intentando entrar");
        this.miObservatorio.entrarInvestigador();
        this.investigar();
        this.miObservatorio.salirInvestigador();
    }
}
