package com.Innova4d.dpm;

/**
 * No hay deadlock 
 * Un filosofo puede morir de hambre.
 * 
 * @author Sean Thomas Burger Flores
 * @version v1.0
 *
 */
public class CenaFilosofos {
	// Numero de filosofos
	private static final int num_filosofos = 5;
	
	public static void main (String[] args) {
		Filosofo[] filosofos = new Filosofo[num_filosofos];
		 Monitor monitor = new Monitor(num_filosofos);
		for (int i = 0; i < num_filosofos; i++) {
			filosofos[i] = new Filosofo(i, monitor);
			new Thread(filosofos[i]).start();
		}
	}
}