package com.Innova4d.DP;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** El problema de los filosofos
 * Aquí si genero un deadlock,
 * @author Sean Thomas Burger Flores ID 146857
 * @version 1.0
 */
public class CenaFilosofos {
	// numero de filosofos
	private static final int NUM_FILOSOFOS = 6; 

	/**
	 * Una prueba de los filosofos.
	 * @param args Not used
	 */
	public static void main (String[] args) {
		
		ReentrantLock[]  tenedores;
		tenedores = new ReentrantLock[NUM_FILOSOFOS];
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			tenedores[i] = new ReentrantLock();
		}
		
		for(int i=0; i< NUM_FILOSOFOS;i++){
			Filosofo filosofo = new Filosofo(i, tenedores[i], tenedores[(i+1)%NUM_FILOSOFOS]);
			new Thread(filosofo).start();
		}
		
	}

}