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
public class Auto extends Vehiculo implements Runnable{
    
    private Surtidor sur;
    
    public Auto(String unaPat, String unMod, String unaMarca, int unaCant, Surtidor unSurtidor){
        super(unaPat, unMod, unaMarca, unaCant);
        sur = unSurtidor;
    }
    public void run(){
        if(this.getEstaEnReserva()){
            try {
                System.out.println(Thread.currentThread().getName()+" está cargando nafta");
                Thread.sleep(1000);
                if (sur.cargarNafta(this, 0))
                    System.out.println("Se cargó nafta");
                else
                    System.out.println("No se cargó nafta");
                System.out.println("Cantidad de nafta en el surtidor: "+sur.getCantNafta());
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
