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
    �� �ڵ忡���� ������ Ŀ���ٰ� �پ����� �� ���ÿ��� ������ ��ü���� ������ �÷��Ͱ� ȸ������ �ʴ´�.
    �� ������ �� ��ü���� �� �� ����(obsolete reference)�� ������ ������ �ֱ� �����̴�.
    ���ÿ� ��� �״ٰ� ���� ������ ������ �����ϰ� �ִ� �޸𸮴� �پ���� �ʴ´�.
    ������ ����(���ǹ��� ������ ���� �ִ� �κ�)�� elements�迭�� �ε����� size ���� ���� �κ��̰�, �� ������ ū �κп� �ִ� ������ �ʿ���� �޸𸮸� �����ϰ� �ִ� �κ��̴�.
    */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    
    /** �ش� ������ �� ���� �� **null ó��(���� ����)** �ϸ� �ȴ�.
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // �� �� ���� ����
        return result;
    }
    */

    /**
     * ���Ҹ� ���� ������ ��� �ϳ� �̻� Ȯ���Ѵ�.
     * �迭 ũ�⸦ �÷��� �� ������ �뷫 �� �辿 �ø���.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    
}


