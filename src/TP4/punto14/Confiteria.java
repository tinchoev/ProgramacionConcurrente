/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.punto14;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Martín
 */
public class Confiteria {
    private Semaphore semConfiteria = new Semaphore(2, true);
    private Semaphore semMozo = new Semaphore(0, true);
    private Semaphore semCocinero = new Semaphore(0, true);
    private Semaphore semServicio = new Semaphore(0, true);
    private Semaphore semEsperaCocina = new Semaphore(0, true);
    
    public Confiteria() {
        
    }
    
    private int elegir() {
        double eleccion = Math.random()*2+1;
        return (int) eleccion;
    }
    
    public void acercarse(String color) {
        if (semConfiteria.tryAcquire()) {
            System.out.println(color+"La confitería está libre, así que entro");
            int eleccion = elegir();
                if (eleccion == 1) {
                    pedirBebida(color);
                }
                if (eleccion == 2) {
                    pedirComida(color);
                }
                if (eleccion == 3) {
                    System.out.println(color+"Pido bebida y comida");
                    pedirBebida(color);
                    pedirComida(color);
                }
                System.out.println(color+"Empleado terminó de comer y deja la cafetería");
                semConfiteria.release();//Libera la confiteria
        } else {
            System.out.println(color+"La confitería está ocupada así que vuelvo al trabajo");
        }
    }
    
    private void pedirBebida(String color) {
        System.out.println(color+"Pido bebida");
        semMozo.release();
        try {
            semServicio.acquire();//Espera que el mozo le sirva la comida
            System.out.println(color+"Empleado está bebiendo");
            Thread.sleep(1500);
        } catch (InterruptedException ex) {}
    }
    
    private void pedirComida(String color) {
        System.out.println(color+"Pido comida");
        semCocinero.release();//Libera al cocinero para que cocine
        try {
            semEsperaCocina.acquire();//Espera que el cocinero le sirva la comida
            System.out.println(color+"Empleado está comiendo");
            Thread.sleep(1500);
        } catch (InterruptedException ex) {}
    }
    
    public void trabajarMozo(String color) {
        System.out.println(color+"Esperando empleado");
        try {
            semMozo.acquire();//Espera a que lo libere un empleado
            System.out.println(color+"Llega un cliente, el mozo lo atiende y busca bebida");
            Thread.sleep(1000);
            System.out.println(color+"El mozo le sirve la bebida al empleado");
            semServicio.release();//Le sirve la comida al empleado
        } catch (InterruptedException ex) {}
    }
    
    public void trabajarCocinero(String color) {
        System.out.println(color+"El cocinero espera a que el empleado le indique qué cocinar");
        try {
            semCocinero.acquire();//Espera a que el mozo le indique qué cocinar
            System.out.println(color+"El cocinero está cocinando");
            Thread.sleep(1000);
            System.out.println(color+"El cocinero terminó de cocinar");
            semEsperaCocina.release();//Le entrega la comida al empleado
        } catch (InterruptedException ex) {}
    }
}
