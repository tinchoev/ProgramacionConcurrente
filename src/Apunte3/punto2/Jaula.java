/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto2;

/**
 *
 * @author Martín
 */
public class Jaula {
    private Plato miPlato;
    private Rueda miRueda;
    
    public Jaula(Plato unPlato, Rueda unaRueda) {
        miPlato = unPlato;
        miRueda = unaRueda;
    }
    
    public void comer(String miNombre) {
        miPlato.empezarAComer(miNombre);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        miPlato.terminarDeComer(miNombre);
    }
    
    public void rodar(String miNombre) {
        miRueda.rodar(miNombre);
    }
}
