/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

import TP2.Cliente;

/**
 *
 * @author Martín
 */
public class testeoRecurso {
    public static void main (String [] args){
        Cliente juan = new Cliente();
        Thread t1 = new Thread(juan, "Juan Lopez");
        Cliente ines = new Cliente();
        Thread t2 = new Thread(ines, "Ines Garcia");
        t1.start();
        t2.start();
    }
}
