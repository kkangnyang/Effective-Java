# ITEM65. 리플렉션보다는 인터페이스를 사용하라


### 리플렉션

리플렉션 기능(java.lang.reflect)을 이용하면 프로그램에서 임의의 클래스에 접근할 수 있다. Class 객체가 주어지면 그 클래스의 생성자, 메서드, 필드에 해당하는 Constructor, Method, Field 인스턴스를 가져올 수 있고, 이어서 이 인스턴스들로는 그 클래스의 멤버 이름, 필드 타입, 메서드 시그니처 등을 가져올 수 있다.


또한 Constructor, Method, Field 인스턴스를 이용해 각각에 연결된 실제 생성자, 메서드, 필드를 조작할 수도 있다. 예를 들어 Method.invoke는 어떤 클래스의 어떤 객체가 가진 어떤 메서드라도 호출할 수 있다. 


### 리플렉션 단점

- 컴파일타임 타입 검사가 주는 이점을 하나도 누릴 수 없다.
- 리플렉션을 이용하면 코드가 지저분해지고 가독성이 떨어진다.
- 리플렉션을 통한 메서드 호출은 일반 메서드 호출보다 훨씬 느리다.
- 리플렉션은 아주 제한된 형태로만 사용해야 그 단점을 피하고 이점만 취할 수 있다.


예를 들어 Set<String> 인터페이스의 인스턴스를 생성하는데, 정확한 클래스는 명렬줄의 첫 번째 인수로 확정한다. 그리고 메서드 호출은 인터페이스를 통해서 호출 한다.


```java
public class ReflectiveInstantiation {

	public static void main(String[] args) {
		// 클래스 이름을 Class 객체로 변환
	    Class<? extends Set<String>> cl = null;
	    try {
	    	cl = (Class<? extends Set<String>>)  // 비검사 형변환!
	    			Class.forName("java.util.HashSet");
	    } catch (ClassNotFoundException e) {
	    	fatalError("클래스를 찾을 수 없습니다.");
	    }

	    // 생성자를 얻는다.
	    Constructor<? extends Set<String>> cons = null;
	    try {
	    	cons = cl.getDeclaredConstructor();
	    } catch (NoSuchMethodException e) {
	    	fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
	    }

	    // 집합의 인스턴스를 만든다.
	    Set<String> s = null;
	    try {
	    	s = cons.newInstance();
	    } catch (IllegalAccessException e) {
	    	fatalError("생성자에 접근할 수 없습니다.");
	    } catch (InstantiationException e) {
	    	fatalError("클래스를 인스턴스화할 수 없습니다.");
	    } catch (InvocationTargetException e) {
	    	fatalError("생성자가 예외를 던졌습니다: " + e.getCause());
	    } catch (ClassCastException e) {
	    	fatalError("Set을 구현하지 않은 클래스입니다.");
	    }

	    // 생성한 집합을 사용한다.
	    s.addAll(Arrays.asList("a","b","c").subList(1, 3));
	    System.out.println(s);

	}
	
	private static void fatalError(String msg) {
	    System.err.println(msg);
	    System.exit(1);
	}

}
```


위의 코드는 리플렉션의 두 가지 단점을 보여준다.

- 리플렉션 없이 생성했다면 컴파일 타임에 잡아낼 수 있는 예외지만, 런타임 시 총 여섯가지나 되는 예외를 던질 수 있다.

- 클래스 이름만으로 인스턴스를 생성해내기 위해 25줄이나 되는 코드를 작성했다. 또한 리플렉션 예외 각각을 잡는 대신 모든 리플렉션 예외의 상위 클래스인 ReflectOperationException을 잡도록 하여 코드 길이를 줄일 수 있다.



컴파일 타임에 알 수 없는 클래스를 사용해야 한다면 리플렉션을 사용할 수 밖에 없을 것이다. 이 경우에도 객체 생성에만 리플렉션을 사용하고 객체를 사용할 때는 적절한 인터페이스나 상위 클래스로 사용하자.


=> 리플렉션은 단점을 피하고 이점만 취할 수 있는 아주 제한된 형태로만 사용하자!
