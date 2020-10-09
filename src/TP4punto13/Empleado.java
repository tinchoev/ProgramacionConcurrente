/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto13;

/**
 *
 * @author Martín
 */
public class Empleado implements Runnable {
    private Confiteria conf;
    private String color;
    
    public Empleado(Confiteria unaConfiteria, String unColor) {
        conf = unaConfiteria;
        color = unColor;
    }
    
    private void acercarseAConfiteria(){
        try {
            System.out.println(color+"Me estoy acercando a la confiteria");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
    }
    
    private void trabajar() {
        try {
            System.out.println(color+"Estoy trabajando");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {}
    }
    
    @Override
    public void run() {
        System.out.println(color+"Soy un empleado");
        //while (true) {
            trabajar();
            System.out.println(color+"Me acerco a la confiteria");
            conf.acercarse(color);
        //}
    }
}
