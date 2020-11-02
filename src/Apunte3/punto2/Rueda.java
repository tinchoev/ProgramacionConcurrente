/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto2;

/**
 *
 * @author Martín
 */
public class Rueda {
    
    public Rueda() {
        
    }

    public synchronized void rodar(String nombre) {
        System.out.println(nombre + " empieza a rodar");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        } finally {
            System.out.println(nombre + " terminó de rodar");
        }
    }
}
