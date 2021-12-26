## ITEM25. 톱레벨 클래스는 한 파일에 하나만 담으라

톱레벨 클래스는 java 파일 하나에 하나만 선언하라는 내용이다. 
굳이 하나의 파일에 담고 싶으면 정적 멤버 클래스를 사용하는 방법을 고민해볼 수 있다.

```
// 코드 25-3 톱레벨 클래스들을 정적 멤버 클래스로 바꿔본 모습 (151-152쪽)
public class Test {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }
}
```
