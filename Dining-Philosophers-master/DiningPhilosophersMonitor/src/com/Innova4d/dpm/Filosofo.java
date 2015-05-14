package com.Innova4d.dpm;

import java.util.Random;

/**
 * La clase Filosofo que utiliza Runnable para poder ser implementada por un thread.
 * @author Sean Thomas Burger Flores ID 146857
 * @version v1.0
 *
 */
public class Filosofo implements Runnable {
	// Genera un numero aleatorio para cuanto tiempo come un filosofo.
	private Random numGenerator = new Random();

	// El id unico de cada filosofo.
	private int id;

	// Un monitor que controla los recursos.
	private Monitor monitor;

	/**
	 * Constructor para el filosofo
	 * @param id el id unico
	 * @param monitor el que controla los recursos
	 */
	public Filosofo (int id, Monitor monitor) {
		this.id = id;
		this.monitor = monitor;
	}

	/**
	 * Piensa, come y baja cubiertos.
	 */
	public void run() {
		try {
			while (true) {
				piensa();
				monitor.levantaCubiertos(id);
				come();
				monitor.bajarCubiertos(id);
			}
		} catch (InterruptedException e) {
			System.out.println("Filosofo: " + id + " interrumpido.\n");
		}
	}

	/**
	 * Permite que el filosofo piense por un tiempo aleatorio
	 * @throws InterruptedException
	 */
	private void piensa() throws InterruptedException {
		System.out.println("Filosofo " + id + " esta pensando.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/**
	 * Permite que el filosofo coma por un tiempo aleatorio.
	 * @throws InterruptedException
	 */
	private void come() throws InterruptedException {
		
		System.out.println("El filosofo " + id + " esta comiendo.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}
}
