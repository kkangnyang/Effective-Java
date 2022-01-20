# ITEM40. @Override �ֳ����̼��� �ϰ��ǰ� ����϶�

�̹� �������� �ٽ��� ���� Ŭ������ �޼��带 �������Ϸ��� ��� �޼��忡 @Override �ֳ����̼��� ���� �̴�. 
����Ŭ������ ����� ����Ŭ��������, ����Ŭ������ �ִ� �޼��� �̸��� ������ �޼��带 ������ ���, ����ġ ���� ���װ� ���� �� �ֱ� �����̴�.

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


���� ���� Bigram.java �� equals �޼���� hashCode �޼��带 �����ߴ�. 
������ @Override �ֳ����̼��� ������� �ʾ����Ƿ�, Object�� equals�� hashCode�� �������ǰ� �ǹ��� ���̴�. 
����, main �޼��忡�� ������ Set�� �ǵ�ġ���� �����Ͱ� ���� �ȴ�.


���� IDE���� @Override�� ���� �ʾҰų� �߸� ������ ���, ������� ���̴�. 
������ �߻� Ŭ������ �������̽������� ���� Ŭ������ ���� �������̽��� �޼��带 �������ϴ� ��� �޼��忡 @Override�� �ٴ� ������ ��� ���� ����.



