package effective.java.item6.flyweightpattern;

import java.util.Scanner;

public class FlyWeightPatternMain {

	public static void main(String[] args) {
		/* 참조 URL : https://velog.io/@hoit_98/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-Flyweight-%ED%8C%A8%ED%84%B4 
		 * https://velog.io/@hoit_98/Assignment-StringBuffer-and-String-Builder
		 * */
		
		/** FlyWeight(플라이웨이트) 패턴 : 인스턴스를 가능한 한 공유해서 사용함으로써 메모리를 절약하는 패턴 */
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("원하는 색을 입력해주세요: ");
		for (int i = 0; i < 10; i++) {
            String input = scanner.nextLine();
            Tree tree = (Tree)TreeFactory.getTree(input);
            tree.setX((int) (Math.random()*100));
            tree.setY((int) (Math.random()*100));
            tree.install();
		}
		
		
	}

}
