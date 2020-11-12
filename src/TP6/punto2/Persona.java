/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto2;

/**
 *
 * @author Martín
 */
public class Persona implements Runnable {
    private GestorSala miSala;
    private boolean esJubilado;
    
    public Persona(GestorSala unaSala, boolean unBoolean) {
        miSala = unaSala;
        esJubilado = unBoolean;
    }
    
    public void run() {
        if (esJubilado) {
            miSala.entrarSalaJubilado();
        } else {
            miSala.entrarSala();
        }
        try {
            Thread.sleep( (long) (Math.random()+3)*1000 );
        } catch (InterruptedException ex) {}
        miSala.salirSala();
    }
}
