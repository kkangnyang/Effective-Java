package effective.java.chapter8.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item52Main2 {

	public static void main(String[] args) {
		// 1��
		new Thread(System.out::println).start();
		// 2��
		ExecutorService exec = Executors.newCachedThreadPool();
		//exec.submit(System.out::println);
	}

}
