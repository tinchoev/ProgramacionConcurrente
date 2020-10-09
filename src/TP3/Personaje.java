/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Personaje implements Runnable{
    private Vida v = new Vida();
    
    private synchronized void operarVida(int unaCant) throws InterruptedException{
        if (v.getVida()<=0){
            System.out.println("No tiene vida.\nSu vida es de: "+v.getVida());
            Thread.sleep( 1000 );
        } else {
            if (Thread.currentThread().getName().equals("ORCO")){
                System.out.println("ORCO: está quitando "+unaCant+" de vitalidad");
                Thread.sleep( 1000 );
                v.restarVida(unaCant);
                System.out.println("ORCO: se quitó "+unaCant+" de vitalidad");
                System.out.println("ORCO: ahora tenés "+v.getVida()+" de vitalidad");
            }
            if (Thread.currentThread().getName().equals("CURANDERO")) {
                System.out.println("CURANDERO: está dando "+unaCant+" de vitalidad");
                Thread.sleep( 1000 );
                v.sumarVida(unaCant);
                System.out.println("CURANDERO: se dió "+unaCant+" de vitalidad");
                System.out.println("CURANDERO: ahora tenés "+v.getVida()+" de vitalidad");
            }
        }
    }
    public void run(){
        try {
            this.operarVida(3);
        } catch (InterruptedException n){
            Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null , n);
        }
    }
}
