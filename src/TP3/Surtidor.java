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
public class Surtidor {
    private int cantNafta = 100;
    
    public Surtidor(){
        
    }
    public synchronized int getCantNafta(){
        return cantNafta;
    }
    public synchronized boolean cargarNafta(Auto unAuto, int cant){
        boolean salida = false;
        if (cantNafta>0){
            cantNafta = cantNafta-cant;
            unAuto.setEstaEnReserva(false);
            salida = true;
        }
        return salida;
    }
}
