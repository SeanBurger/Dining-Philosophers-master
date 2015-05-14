package com.Innova4d.dpm;

/**
 * El monitor se asegura de que el filosofo levante los cubiertos 
 * siempre y cuando sus vecinos no esten comiendo
 *
 * @author Sean Thomas Burger Flores ID 146857
 * @version 1.0v
 *
 */
class Monitor {
	// El monitor conoce los diferentes estados para cada filósofo.
	private enum State {PENSANDO, HAMBRIENTO, COMIENDO};

	// Un vector que contiene el estado de cada filósofo.
	private State[] estadoFilosofo;

	/**
	 * Constructor que crea un monitor para el numero adecuado de filosofos
	 * Como estado inicial, todos los filosofos estan pensando.
	 *
	 * @param numFilosofos El numero de filosofos.
	 */
	public Monitor (int numFilosofos) {
		estadoFilosofo = new State[numFilosofos];
		for (int i = 0; i < estadoFilosofo.length; i++) {
			estadoFilosofo[i] = State.PENSANDO;
		}
	}

	/**
	 * Un filosofo toma ambos cubiertos.
	 * El filosofo se pone a pensar si ambos vecinos comen.
	 *
	 * @param idFilosofo El filosofo que desea comer.
	 * @throws InterruptedException excepcion si falla el hilo,
	 */
	public synchronized void levantaCubiertos(int idFilosofo) throws InterruptedException {
		// Si levanta los cubiertos, come.
		estadoFilosofo[idFilosofo] = State.HAMBRIENTO;
		System.out.println("Filosofo: " + idFilosofo + " esta hambriento.\n");
		// Mientras los vecinos comen, esperar...
		while (losVecinosComen(idFilosofo)) {
			try{
				wait();
			    
			}
			catch(InterruptedException a){
				System.out.println(a);
			}
			
		}
		/* Cuando los vecinos dejan de comer.
		 * Ahora, y solo ahora, este filosofo esta comiendo. 
		 */
		estadoFilosofo[idFilosofo] = State.COMIENDO;
		System.out.println("Filosofo: " + idFilosofo + " esta comiendo.\n");
	}

	/**
	 * Regresar true si vecinos no comend.
	 * @param idFilosofo El filosofo objetivo para verificar vecinos.
	 * @return true si ningun vecino come.
	 */
	private boolean losVecinosComen(int idFilosofo) {
		// Verificar filosofo de un lado.
		if (estadoFilosofo[(idFilosofo + 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Verificar filosofo del otro.
		if (estadoFilosofo[(idFilosofo + estadoFilosofo.length - 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Ninguno esta comiendo
		return false;
	}

	/**
	 * El filosofo baja los cubiertos.
	 * 
	 * Notificar a todos que ya pueden ocupar los recursos (Cubiertos).
	 *
	 * @param idFilosofo El filosofo que a terminado de comer.
	 */
	public synchronized void bajarCubiertos(int idFilosofo) {
		estadoFilosofo[idFilosofo] = State.PENSANDO;
		notifyAll();
	}
}
