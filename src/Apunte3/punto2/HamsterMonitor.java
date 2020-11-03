/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto2;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author Martín
 */
public class HamsterMonitor implements Runnable{

    private Jaula miJaula;
    private String miNombre;

    public HamsterMonitor(Jaula unaJaula, String nombre) {
        miJaula = unaJaula;
        miNombre = nombre;
    }

    public void run() {
        try {
            Thread.sleep(((long) Math.random()+1)*1000);
        } catch (InterruptedException ex) {
            
        }
        //while (true) {
            miJaula.comer(miNombre);
            miJaula.rodar(miNombre);
        //}
    }
}
