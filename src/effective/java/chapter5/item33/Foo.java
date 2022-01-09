package effective.java.chapter5.item33;

public class Foo {
	
	@FooAnnotation(test = "StringTest")
	private String value;
	
	public Foo(String value) {
		this.value = value;
	}

}
