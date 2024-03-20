package controller;

import java.util.concurrent.Semaphore;

public class Metodos1 extends Thread {
	
	private int id;
	private Semaphore semaforo;
	
	public Metodos1(int id , Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	public void run() {
		if (id % 3 == 1 ) {
			int sleepTransac = 1000;
			
			for (int i = 0; i < 2; i++) {
				int sleepCalc = (int) ((Math.random() * 801) + 200);
				calculo(sleepCalc , id);
				try {
					semaforo.acquire();	
					info(id);
					transacao(sleepTransac , id);
					System.err.println("#" + id + " SAIU da secao critica");
				} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				semaforo.release();
			}					
			}			
		} else if (id % 3 == 2) {			
			int sleepTransac = 1500;
			
			for (int i = 0; i < 3; i++) {
				int sleepCalc = (int) ((Math.random() * 1001) + 500);
				calculo(sleepCalc , id);
				try {
					semaforo.acquire();	
					info(id);
					transacao(sleepTransac , id);
					System.err.println("#" + id + " SAIU da secao critica");
				} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				semaforo.release();
			}
			}
		} else if (id % 3 == 0) {					
			int sleepTransac = 1500;
				
					for (int i = 0; i < 3; i++) {
						int sleepCalc = (int) ((Math.random() * 1001) + 1000);
						calculo(sleepCalc , id);
						try {
							semaforo.acquire();	
							info(id);
							transacao(sleepTransac , id);
							System.err.println("#" + id + " SAIU da secao critica");
						} catch (InterruptedException e) {
						System.err.println(e.getMessage());
					} finally {
						semaforo.release();
					}
					}		
		}
	}
	private void info(int id) {
		System.err.println("\n#" + id + " ENTROU em secao critica (Resto=" + id%3 + ")");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void calculo(int sleepCalc, int id) {
		
		System.out.println("#" + id + " esta fazendo calculos por " + sleepCalc + "ms.");
		try {
			sleep(sleepCalc);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	private void transacao(int sleepTransac, int id) {
		System.out.println("#" + id + " esta fazendo transacoes de BD por " + sleepTransac + "ms.");
		try {
			sleep(sleepTransac);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
