## ITEM25. �鷹�� Ŭ������ �� ���Ͽ� �ϳ��� ������

�鷹�� Ŭ������ java ���� �ϳ��� �ϳ��� �����϶�� �����̴�. 
���� �ϳ��� ���Ͽ� ��� ������ ���� ��� Ŭ������ ����ϴ� ����� ����غ� �� �ִ�.

```
// �ڵ� 25-3 �鷹�� Ŭ�������� ���� ��� Ŭ������ �ٲ㺻 ��� (151-152��)
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
