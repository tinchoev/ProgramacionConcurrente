/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion.concurrente;

/**
 *
 * @author Martín
 */
public class PingPong extends Thread {
    private int delay;            //info de las iteraciones
    private Dato miD;          // para mantener el total
    private int miCta = 0;    // para contar mis iteraciones

    // constructor 1, que refina al constructor heredado de Thread
    public PingPong(String cartel, int cantMs){
        super(cartel);
        this.delay = cantMs;
    }  

    // constructor 2, que utiliza al constructor 1
    public PingPong(String cartel, int cantMs, Dato dd){
        this(cartel, cantMs);
        this.miD = dd;
    }

    public void run(){

        for (int i=1; i< delay * 2; i++){
        // System.out.print(this.getName() + " ");
        miCta ++;
        this.miD.contar();

        } // del for
        System.out.println(miCta + " veces " + this.getName());
    }
}
