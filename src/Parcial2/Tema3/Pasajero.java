/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema3;

/**
 *
 * @author Martín
 */
public class Pasajero implements Runnable {
    private Control ctrl;

    public Pasajero(Control ctrl) {
        this.ctrl = ctrl;
    }
    
    public void run() {
        while (true) {
            ctrl.subir();
            ctrl.bajar();
        }
    }
}
