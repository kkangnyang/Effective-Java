package effective.java.item8;

public class SampleFinalize implements AutoCloseable {

	private boolean closed;
	
	@Override
	public void close() throws Exception {
		
		if(this.closed) {
			throw new IllegalStateException();
		}
		closed = true;
		System.out.println("close");
		
	}
	
	public void hello() {
		System.out.println("hello");
	}
	
	@Override
	protected void finalize() throws Throwable {
		if(!this.closed) close(); 
	}

}
