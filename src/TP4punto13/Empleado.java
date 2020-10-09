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
    
    private void comer() {
        try {
            System.out.println(color+"Estoy comiendo");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        } finally {
            System.out.println(color+"Muchas gracias mozo!");
        }
    }
    
    @Override
    public void run() {
        System.out.println(color+"Soy un empleado");
        acercarseAConfiteria();
        conf.entrarAConfiteria(color);
        conf.solicitarAtencion(color);
        comer();
        conf.salirDeConfiteria(color);
    }
}
