/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrupoTeoria;

import GrupoTeoria.*;
/**
 *
 * @author Martín
 */
public class Caja {
    private int pesoActual;
    
    public Caja() {
        pesoActual = 0;
    }
    
    public int getPesoActual() {
        return pesoActual;
    }
    
    public void sumarPeso(int unPeso) {
        pesoActual+= unPeso;
    }
}
