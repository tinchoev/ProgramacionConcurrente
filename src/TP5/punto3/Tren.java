/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.punto3;

/**
 *
 * @author Martín
 */
public class Tren implements Runnable{
    private ControlTren control;
    
    public Tren(ControlTren unControl) {
        control = unControl;
    }
    
    public void run() {
        while (true) {
            control.iniciarRecorrido();
        }
    }
}
