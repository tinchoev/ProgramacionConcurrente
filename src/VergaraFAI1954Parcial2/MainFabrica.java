/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

/**
 *
 * @author Martín
 */
public class MainFabrica {
    public static void main(String[] args) {
        //Creación instancias
        int cantMaxMangas = 10, cantMaxCuerpos = 5, cantMaxSueters = 10;
        Taller elTaller = new Taller(cantMaxMangas, cantMaxCuerpos, cantMaxSueters);
        CostureraMangas mangas = new CostureraMangas(elTaller);
        CostureraCuerpo cuerpos = new CostureraCuerpo(elTaller);
        CostureraEnsamble sueters = new CostureraEnsamble(elTaller);
        ContadorCajas cont = new ContadorCajas(elTaller);
        //Creación hilos
        Thread[] hilos = new Thread[4];
        hilos[0] = new Thread(mangas, "Costurera de mangas");
        hilos[1] = new Thread(cuerpos, "Costurera de cuerpos");
        hilos[2] = new Thread(sueters, "Costurera de sueters");
        hilos[3] = new Thread(cont, "Contador");
        //Start hilos
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    }
}
