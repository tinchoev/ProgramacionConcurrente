/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto13;

/**
 *
 * @author Martín
 */
public class main2 {
    public static void main(String [] args) {
        Confiteria conf = new Confiteria();
        Mozo mozo = new Mozo(conf, "\u001B[31m");
        Empleado empleado = new Empleado(conf, "\u001B[33m");
        Thread hiloMozo = new Thread(mozo), hiloEmpleado = new Thread(empleado);
        hiloMozo.start(); hiloEmpleado.start();
    }
}
