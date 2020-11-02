/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto3;

/**
 *
 * @author Martín
 */
public class Barbero implements Runnable{
    private Barberia miBarberia;
    
    public Barbero(Barberia unaBarberia) {
        miBarberia = unaBarberia;
    }
    
    public void run() {
        while (true) {
            miBarberia.esperarCliente();
        }
    }
}
