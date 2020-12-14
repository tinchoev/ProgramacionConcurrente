/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class MontañaRusa implements Runnable {
    private Control ctrl;
    private int cantRecorridos;
    
    public MontañaRusa(Control unCtrl, int unaCant) {
        ctrl = unCtrl;
        cantRecorridos = unaCant;
    }
    
    public void run() {
        while (cantRecorridos > 0) {
            ctrl.recorrer();
            System.out.println(Thread.currentThread().getName()+": Empieza recorrido");
            try {
                Thread.sleep((int) (Math.random()+2)*4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MontañaRusa.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cantRecorridos--;
                if (cantRecorridos == 0) {
                    ctrl.cerrar();
                } else {
                    ctrl.terminar();
                }
                System.out.println(Thread.currentThread().getName()+": Termina recorrido");
            }
        }
        System.out.println(Thread.currentThread().getName()+": Cierra la montaña");
    }
}
