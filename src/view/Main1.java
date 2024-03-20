package view;
import controller.Metodos1;
import java.util.concurrent.Semaphore;

public class Main1 {
	
	public static void main (String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int id = 1; id < 22; id++) {
			Thread semaforos = new Metodos1(id , semaforo);
			semaforos.start();
		}
	}
}
