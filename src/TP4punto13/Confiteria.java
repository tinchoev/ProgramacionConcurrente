/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto13;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Martín
 */
public class Confiteria {
    private Semaphore semConfiteria = new Semaphore(1, true);
    private Semaphore semMozo = new Semaphore(0, true);
    private Semaphore semCocinero = new Semaphore(0, true);
    private Semaphore semServicio = new Semaphore(0, true);
    private Semaphore semEsperaCocina = new Semaphore(0, true);
    
    public Confiteria() {
        
    }
    
    public void acercarse(String color) {
        if (semConfiteria.tryAcquire()) {
            System.out.println(color+"La confitería está libre, así que entro");
            semMozo.release();
            try {
                semServicio.acquire();//Espera que el mozo le sirva la comida
                System.out.println(color+"Empleado está comiendo");
                Thread.sleep(1500);
                System.out.println(color+"Empleado terminó de comer y deja la cafetería");
                semConfiteria.release();//Libera la confiteria
            } catch (InterruptedException ex) {}
        } else {
            System.out.println(color+"La confitería está ocupada así que vuelvo al trabajo");
        }
    }
    
    public void trabajarMozo(String color) {
        System.out.println(color+"Esperando empleado");
        try {
            semMozo.acquire();//Espera a que lo libere un empleado
            System.out.println(color+"Llega un cliente, el mozo lo atiende y le indica al cocinero el pedido");
            semCocinero.release();//Libera al cocinero para que cocine
            semEsperaCocina.acquire();//Espera a que el cocinero le entregue la comida
            System.out.println(color+"El mozo le sirve la comida al empleado");
            semServicio.release();//Le sirve la comida al empleado
        } catch (InterruptedException ex) {}
    }
    
    public void trabajarCocinero(String color) {
        System.out.println(color+"El cocinero espera a que el mozo le indique qué cocinar");
        try {
            semCocinero.acquire();//Espera a que el mozo le indique qué cocinar
            System.out.println(color+"El cocinero está cocinando");
            Thread.sleep(1000);
            System.out.println(color+"El cocinero terminó de cocinar");
            semEsperaCocina.release();//Le entrega la comida al mozo para que la sirva
        } catch (InterruptedException ex) {}
    }
}
