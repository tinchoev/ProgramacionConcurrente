/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3;

/**
 *
 * @author Martín
 */
public class MainPunto4 {
    public static void main(String [] args){
        Surtidor sur = new Surtidor();
        Auto a = new Auto("LCH-600", "Uno", "Fiat", 156, sur);
        int cant = 5, i;
        Thread[] hilos = new Thread[cant];
        for (i=0; i<cant; i++) {
            hilos[i] = new Thread(a, String.valueOf(i));
        }
        for (i=0; i<cant; i++){
            hilos[i].start();
        }
    }
}
