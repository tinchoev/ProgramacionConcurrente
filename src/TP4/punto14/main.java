/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.punto14;


/**
 *
 * @author Martín
 */
public class main {
    public static void main(String [] args) {
        int cantEmpleados = 6;
        Confiteria conf = new Confiteria();
        Cocinero cook1 = new Cocinero(conf, "\u001B[30m");
        Cocinero cook2 = new Cocinero(conf, "\u001B[30m");
        Mozo mozo = new Mozo(conf, "\u001B[31m");
        Empleado[] empleados = new Empleado[cantEmpleados];
        Thread[] hilos = new Thread[cantEmpleados+3];
        empleados[0] = new Empleado(conf, "\u001B[32m");
        empleados[1] = new Empleado(conf, "\u001B[33m");
        empleados[2] = new Empleado(conf, "\u001B[34m");
        empleados[3] = new Empleado(conf, "\u001B[35m");
        empleados[4] = new Empleado(conf, "\u001B[36m");
        empleados[5] = new Empleado(conf, "\u001B[37m");
        hilos[cantEmpleados] = new Thread(mozo);
        hilos[cantEmpleados+1] = new Thread(cook1);
        hilos[cantEmpleados+2] = new Thread(cook2);
        for (int i=0; i<cantEmpleados; i++) {
            hilos[i] = new Thread(empleados[i]);
        }
        for (int i=0; i<cantEmpleados+3; i++) {
            hilos[i].start();
        }
    }
}
