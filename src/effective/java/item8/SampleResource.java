package effective.java.item8;

public class SampleResource implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("close");
	}
	
	public void hello() {
		System.out.println("hello");
	}
	
}
