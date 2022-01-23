# ITEM43. ���ٺ��ٴ� �޼��� ������ ����϶�.

���ٰ� �͸� Ŭ�������� ���� �� �߿��� ���� ū Ư¡�� �������ε�, ���ٺ��� �� �����ϰ� ����� ����� �޼��� ������.

## �޼��� ������

Map�� �ϳ� ����ǵ�, int ���� key�� ������, �ش� ���� �󵵼��� value�� �����ϴ� map�� ������.


Map�� �߰��� merge�޼��带 ����ߴ�. merge �޼���� Ű, ��, �Լ��� �μ��� �޾Ƽ� �־��� Ű�� �� �ȿ� ���� ���ٸ� {key, value} ���� �״�� �����ϰ�, �̹� �ִٸ�, �Լ��� ���� ���� �־��� ���� ������ ����, �� ����� ���� ���� �����.

```java
Map<Integer, Integer> map = new HashMap<>();
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);

map.merge(2, 1, (oldVal, newVal) -> oldVal + newVal);

System.out.println(map.toString());
// {1=3, 2=1}
```


���⼭ oldVal �� newVal�� ũ�� �ϴ� �� ���� ������ �����Ѵ�. �� ���� �ϴ� ����� �� ���� �����ϴ� ���̴�. Integer Ŭ������ ���� �޼��� sum�� �����ϸ� �ξ� ������ �ڵ带 �ۼ��� �� �ִ�.

```java
map.merge(1, 1, Integer::sum);
```

���ٷ� �������� �� �ʹ� ��ų� �����ϴٸ�, �� ������ �޼���� ����� ���� ��� �� �޼��� ������ ����ϴ� ����� �ִ�.


���� ���ٰ� �޼��� �������� ������ ���� �ִ�. �ַ� �޼���� ���ٰ� ���� Ŭ������ ���� �� �׷���.

## �޼��� ������ ���� 5����

|�޼��� ���� ���� |�� |���� ����� �ϴ� ���� |
|���� | Integer::parseInt | str -> Integer.parseInt(str) |
|������(�ν��Ͻ�) | Instant.now()::isAfter | Instant then = Instant.now(); t -> then.isAfter() |
|��������(�ν��Ͻ�) | String::toLowerCase | str -> str.toLowerCase() |
|Ŭ���� ������ |TreeMap<K,V>::new | () -> new TreeMap<K,V>() |
|�迭 ������ |int[]::new |len -> new int[len] |

- ������(�ν��Ͻ�) : ���� ��ü(���� ��� �ν��Ͻ�)�� Ư���ϴ� ������ �ν��Ͻ� �޼��� ����
- ��������(�ν��Ͻ�) : ���Ű�ü�� Ư������ ����
