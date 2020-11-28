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
public class Visitante implements Runnable {
    private Observatorio miObservatorio;
    private boolean usaSillaDeRuedas;
    
    public Visitante(Observatorio unObservatorio) {
        miObservatorio = unObservatorio;
        usaSillaDeRuedas = this.definirEstado();
    }
    
    private boolean definirEstado() {
        boolean salida;
        double aux = (double) Math.random();
        if (aux > 0.80) {
            salida = true;
        } else {
            salida = false;
        }
        return salida;
    }
    
    private void recorrer() {
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
        } finally {
            System.out.println(Thread.currentThread().getName()+": intentando entrar");
            if (usaSillaDeRuedas) {    
                this.miObservatorio.entrarVisitanteEnSillaDeRuedas();
                this.recorrer();
                this.miObservatorio.salirVisitanteEnSillaDeRuedas();
            } else {
                this.miObservatorio.entrarVisitante();
                this.recorrer();
                this.miObservatorio.salirVisitante();
            }
        }
    }
}
