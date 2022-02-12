package effective.java.chapter8.item52;

import java.util.List;

public class WineMain {

	static class Wine {
	    String name() { return "������"; }
	}

	static class SparklingWine extends Wine {
	    @Override String name() { return "������ ������"; }
	}

	static class Champagne extends SparklingWine {
	    @Override String name() { return "������"; }
	}

	public static void main(String[] args) {
	    List<Wine> wineList = List.of(
	            new Wine(), new SparklingWine(), new Champagne());

	    for (Wine wine : wineList)
	        System.out.println(wine.name());
	}

}
