# ITEM40. @Override 애너테이션을 일관되게 사용하라

이번 아이템의 핵심은 상위 클래스의 메서드를 재정의하려는 모든 메서드에 @Override 애너테이션을 달자 이다. 
상위클래스를 상속한 하위클래스에서, 상위클래스에 있는 메서드 이름과 동일한 메서드를 정의할 경우, 예상치 못한 버그가 생길 수 있기 때문이다.

```java
public class Bigram {
	private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first  = first;
        this.second = second;
    }

    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram(ch, ch));
        System.out.println(s.size());
    }
}
```


위와 같은 Bigram.java 는 equals 메서드와 hashCode 메서드를 정의했다. 
하지만 @Override 애너테이션을 사용하지 않았으므로, Object의 equals와 hashCode에 다중정의가 되버린 것이다. 
따라서, main 메서드에서 실행한 Set에 의도치않은 데이터가 들어가게 된다.


보통 IDE에서 @Override를 쓰지 않았거나 잘못 정의한 경우, 경고해줄 것이다. 
하지만 추상 클래스나 인터페이스에서는 상위 클래스나 상위 인터페이스의 메서드를 재정의하는 모든 메서드에 @Override를 다는 습관을 드는 것이 좋다.



