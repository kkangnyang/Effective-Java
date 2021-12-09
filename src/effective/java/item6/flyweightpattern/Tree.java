package effective.java.item6.flyweightpattern;

public class Tree {
	
	private String color;
	private int x;
	private int y;
	
	public Tree(String color) {
		this.color = color;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void install() {
		System.out.println("x:"+x+" y:"+y+" 위치에 "+color+"색 나무를 설치했습니다!");
	}
}
