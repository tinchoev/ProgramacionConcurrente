/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto13;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Martín
 */
public class Confiteria {
    private Semaphore semMozo;
    private Semaphore semEmpleado;
    private Semaphore semConfiteria;
    
    public Confiteria() {
        semMozo = new Semaphore(0);
        semEmpleado = new Semaphore(0);
        semConfiteria = new Semaphore(1);
    }
    
    public void esperarEmpleado(String color) {
        System.out.println(color+"Estoy esperando un empleado");
        //semMozo.release();
    }
    
    public boolean entrarAConfiteria(String color) {
        System.out.println(color+"Entrando a la confitería");
        return semConfiteria.tryAcquire();
    }
    
    public void solicitarAtencion(String color) {
        System.out.println(color+"Solicitando atención");
        try {
            semEmpleado.release();
            semMozo.acquire();
        }
        catch (InterruptedException ex) {}
    }
    
    public void atender(String color) {
        try {
            semEmpleado.acquire();
        } catch (InterruptedException ex) {}
        System.out.println(color+"Atendiendo empleado");
        semMozo.release();
    }
    
    public void terminarAtencion(String color) {
        try {
            semConfiteria.acquire();
        } catch (InterruptedException ex) {}
        System.out.println(color+"Terminé de atender al empleado");
        semConfiteria.release();
    }
    
    public void salirDeConfiteria(String color) {
        System.out.println(color+"Estoy saliendo de la confitería");
        semConfiteria.release();
    }
    
    /*private Lock lockMozo;
    private Lock lockEmpleado;
    private Lock lockConfiteria;
    
    public Confiteria() {
        lockMozo = new ReentrantLock();
        lockEmpleado = new ReentrantLock();
        lockConfiteria = new ReentrantLock();
    }
    
    public void esperarEmpleado(String color) {
        System.out.println(color+"Estoy esperando un empleado");
        lockMozo.lock();
    }
    
    public void entrarAConfiteria(String color) {
        System.out.println(color+"Entrando a la confitería");
        lockConfiteria.lock();
    }
    
    public void solicitarAtencion(String color) {
        System.out.println(color+"Solicitando atención");
        lockMozo.unlock();
        lockEmpleado.lock();
    }
    
    public void atender(String color) {
        System.out.println(color+"Atendiendo empleado");
    }
    
    public void terminarAtencion(String color) {
        System.out.println(color+"Terminé de atender al empleado");
        lockEmpleado.unlock();
    }
    
    public void salirDeConfiteria(String color) {
        System.out.println(color+"Estoy saliendo de la confitería");
        lockConfiteria.unlock();
    }*/
}
