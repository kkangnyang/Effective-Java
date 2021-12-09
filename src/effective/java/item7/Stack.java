package effective.java.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
	
	private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    
    /**
    이 코드에서는 스택이 커졌다가 줄어들었을 때 스택에서 꺼내진 객체들을 가비지 컬렉터가 회수하지 않는다.
    이 스택이 그 객체들의 다 쓴 참조(obsolete reference)를 여전히 가지고 있기 때문이다.
    스택에 계속 쌓다가 많이 빼내도 스택이 차지하고 있는 메모리는 줄어들지 않는다.
    가용한 범위(유의미한 값들을 갖고 있는 부분)는 elements배열의 인덱스가 size 보다 작은 부분이고, 그 값보다 큰 부분에 있는 값들은 필요없이 메모리를 차지하고 있는 부분이다.
    */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    
    /** 해당 참조를 다 썼을 때 **null 처리(참조 해제)** 하면 된다.
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }
    */

    /**
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    
}


