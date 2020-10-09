/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto1;

/**
 *
 * @author Martín
 */
public class DualSynch {
    private Object syncObject = new Object();
    
    public synchronized void f() {
        for (int i=0; i<5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }
    
    public void g() {
        synchronized (syncObject) {
            for (int i=0; i<5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
