/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto5.A;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Coche {
    private char miDireccion;
    private Puente miPuente;
    
    public Coche(Puente unPuente) {
        miPuente = unPuente;
        miDireccion = crearDireccion();
    }
    
    private char crearDireccion() {
        char salida;
        double aux = (double) Math.random();
        if (aux > 0.5) {
            salida = 'n';
        } else {
            salida = 's';
        }
        return salida;
    }
    
    public void run() {
        try {
            Thread.sleep((long) (Math.random()+1)*2000);
            System.out.println(Thread.currentThread().getName()+": quiere pasar el puente por el "+miDireccion);
            miPuente.entrarAlPuente(miDireccion);
            Thread.sleep((long) (Math.random()+1)*3000);
            miPuente.salirDelPuente();
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
