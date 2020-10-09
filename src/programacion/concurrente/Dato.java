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
public class Dato {
    private int valor;

    void contar(){
       valor = ++valor;
    }

    int obtenerValor(){
       return valor;
    }
}
