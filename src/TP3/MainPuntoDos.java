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
public class MainPuntoDos {
    public static void main (String [] args){
        Personaje yo = new Personaje();
        Thread orco = new Thread(yo, "ORCO");
        Thread curandero = new Thread (yo, "CURANDERO");
        orco.start();
        curandero.start();
    }
}
