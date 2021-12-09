package effective.java.item9;

public class MyResource implements AutoCloseable {
	
	public void doSomething() throws FirstError {
		System.out.println("doing something");
		throw new FirstError();
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("clean my resource");
		throw new SecondError();
	}
	
	public class FirstError extends RuntimeException { }
	public class SecondError extends RuntimeException { }

}


