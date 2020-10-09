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
public class Vida {
    private int cant = 10;
    
    public Vida(){
        
    }
    public int getVida(){
        return cant;
    }
    public void sumarVida(int unaCant){
        cant = cant + unaCant;
    }
    public void restarVida(int unaCant){
        cant = cant - unaCant;
    }
}
