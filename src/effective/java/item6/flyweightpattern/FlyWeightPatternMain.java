package effective.java.item6.flyweightpattern;

import java.util.Scanner;

public class FlyWeightPatternMain {

	public static void main(String[] args) {
		/* ���� URL : https://velog.io/@hoit_98/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-Flyweight-%ED%8C%A8%ED%84%B4 
		 * https://velog.io/@hoit_98/Assignment-StringBuffer-and-String-Builder
		 * */
		
		/** FlyWeight(�ö��̿���Ʈ) ���� : �ν��Ͻ��� ������ �� �����ؼ� ��������ν� �޸𸮸� �����ϴ� ���� */
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("���ϴ� ���� �Է����ּ���: ");
		for (int i = 0; i < 10; i++) {
            String input = scanner.nextLine();
            Tree tree = (Tree)TreeFactory.getTree(input);
            tree.setX((int) (Math.random()*100));
            tree.setY((int) (Math.random()*100));
            tree.install();
		}
		
		
	}

}
