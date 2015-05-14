package com.Innova4d.DP;
import java.util.Random;
import java.util.concurrent.locks.Lock;
/**
 * La clase Filosofo que utiliza Runnable para poder ser implementada por un thread.
 * @author Sean Thomas Burger Flores ID 146857
 * @version 1.0
 */
class Filosofo implements Runnable {
	// Tiempo en que come un filósofo
	private Random numGenerator = new Random();
	// El Id del filosofo 
	private int id;

	/* 
	 * Definir los cubiertos del filosofo.
	 * En Java los recursos compartidos se definen como Locks.
	 */
	private Lock cubiertoIzquierdo;
	private Lock cubiertoDerecho;

	/**
	 * El constructor del filosofo
	 * @param id el id del filosofo
	 * @param cubiertoIzquierdo cubierto izquierdo
	 * @param cubiertoDerecho cubierto derecho
	 */
	public Filosofo (int id, Lock cubiertoIzquierdo, Lock cubiertoDerecho) {
		this.id = id;
		this.cubiertoIzquierdo = cubiertoIzquierdo;
		this.cubiertoDerecho = cubiertoDerecho;
	}

	/**
	 * Ejecuta el thread, piensa, levanta cubiertos, come.
	 */
	public void run() {
		try {
			while (true) {
				piensa();
				levantaCubiertoIzquierdo();
				levantaCubiertoDerecho();
				come();
				bajaCubiertos();
			}
		} catch (InterruptedException e) {
			System.out.println("El Filosofo " + id + " fue interrumpido.\n");			
		}
	}

	/** 
	 * El filosofo esta pensando
	 */
	private void piensa() throws InterruptedException {
		System.out.println("El Filosofo " + id + " esta pensando.\n");
		/*
		 * Cuando el filósofo está pensando debemos "dormir el hilo por un tiempo aleatorio"
		 * *** Completar el código ***
		 */
		Thread.sleep(numGenerator.nextInt(10));
	}

	/** 
	 * El filosofo usa  el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoIzquierdo() {
		cubiertoIzquierdo.lock();
		System.out.println("El filosofo " + id  + " tiene en la mano cubierto izquierdo.\n");
	}

	/** 
	 * El filosofo usa el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoDerecho() {
		
		cubiertoDerecho.lock();
		System.out.println("El filosofo" + id +"tiene en la mano el cubierto derecho.\n");
	}

	/**
	 * El filosofo come...
	 * @throws InterruptedException
	 */
	private void come() throws InterruptedException {
		System.out.println("El filosofo " + id + " esta comiendo.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/**
	 * El filosofo baja los cubiertos
	 */
	private void bajaCubiertos() throws InterruptedException {
		
		cubiertoDerecho.unlock();
		cubiertoIzquierdo.unlock();
		System.out.println("El filosofo"+ id +"suelta los cubiertos\n");
		Thread.sleep(numGenerator.nextInt(10));
	}
}