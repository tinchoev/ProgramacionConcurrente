/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto12;


/**
 *
 * @author Martín
 */
public class Hamster implements Runnable{
    private Jaula miJaula;
    private String miColor;
    
    public Hamster(Jaula unaJaula, String unColor) {
        miJaula = unaJaula;
        miColor = unColor;
    }
    
    public void run() {
        miJaula.comerDelPlato(miColor);
        miJaula.correrEnLaRueda(miColor);
        miJaula.descansarEnHamaca(miColor);
    }
}
