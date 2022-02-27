# ITEM55. �ɼų� ��ȯ�� ������ �϶�

### �ڹ� 8 ����

�ڹ� 8 ������ �޼��尡 Ư�� ���ǿ��� ���� ��ȯ�� �� ���� �� �ΰ��� �������� �־���.

1. Exception Throw
- ���ܴ� �ݵ�� �������� ��Ȳ������ ����ؾ� �Ѵ�.
- ���ܴ� ���� ������ ���� ĸó�ϱ� ������ ����� ��δ�.

2. Null Return
- null�� �����ϴ� ��쿡�� Null Pointer Exception�� �׻� �����ؾ� �Ѵ�.

### Optional ����

�ڹ� 8���� Optional<T>�� ���ԵǸ鼭 �������� �ϳ� �þ���.
- Optional�̶� null�� �ƴ� TŸ�� ������ �ϳ� ��ų� �ƹ��͵� ���� ���� ������ ���� Ŭ�����̴�.
- Optional�� ���Ҹ� �ִ� 1�� ���� �� �ִ� �Ҹ� Collection�̴�.
- �ڹ� 8 ������ �ڵ庸�� null-safe�� ������ ó���� �� �ְ� ���ش�.
- Optional�� ��ȯ�Ͽ� �� �� �����ϰ� �ۼ��� �� �ְ� ���ش�.
- ��ȯ���� Optional�� ���� ��ȯ���� ���� ���� ������ Ŭ���̾�Ʈ���� �˷��ش�.

### Optional �޼���

- Optional.empty()
	- ���� ���� ����ִ� Optional ��ü ��ȯ
- Optional.of(T value)
	- ���� ���� value�� Optional ��ü ��ȯ
	- ���� value�� null�� ��� NullPointException ��ȯ
- Optional.ofNullable(T value)
	- ���� ���� ���̴� Optional �������
	- value�� null�̸�, empty Optional�� ��ȯ�ϰ�, ���� ������ Optional.of�� ����
- T get()
	- Optional ���� ���� ��ȯ
	- ���� Optional ���� ���� null�� ��� NoSuchElementException �߻�
- boolean isPresent()
	- Optional ���� ���� null�̸� false, ������ true
	- Optional ���ο����� ����ؾ��ϴ� �޼����� ����
- boolean isEmpty()
	- Optional ������ ���� null�̸� true, ������ false
	- isPresent() �޼����� �ݴ�Ǵ� �޼���
- void ifPresent(Consumer<? super T> consumer)
	- Optional ������ ���� �ִ� ��� consumer �Լ��� ����
- Optional<T> filter(Predicate<T> predicate)
	- Optional�� filter ������ �ɾ� ���ǿ� ���� ���� Optional ���� ���� ����
	- ������ ���� ������ Optional.empty�� ����
- Optional<U> map(Funtion<? super T, ? extends U> f)
	- Optional ������ ���� Function�� ���� ����
- T orElse(T other)
	- Optional ������ ���� �ִ� ��� �� ���� ��ȯ
	- Optional ������ ���� null�� ��� other�� ��ȯ
- T orElseGet(Supplier<? extends T> supplier)
	- Optional ������ ���� �ִ� ��� �� ���� ��ȯ
	- Optional ������ ���� null�� ��� supplier�� ������ ���� ��ȯ
- T orElseThrow()
	- Optional ������ ���� �ִ� ��� �� ���� ��ȯ
	- Optional ������ ���� null�� ��� NoSuchElementException �߻�
- T orElseThrow(Supplier<? extends X> exceptionSupplier)
	- Optional ������ ���� �ִ� ��� �� ���� ��ȯ
	- Optional ������ ���� null�� ��� exceptionSupplier�� �����Ͽ� Exception �߻�	
	

### �⺻�� ����

```java
String lastWord = max(words).orElse("�ܾ����");
```


### ���ϴ� ���� ����

```java
Toy myToy = max(toys).orElseThrow(TemperTantrumException::new);
```


### �׻� ���� ���� ���

```java
Element lastNobleGas = max(Elements.NOBLE_GASES).get();
```

Optional���� ���� ���� �پ��� �޼������ ������ ���� �������� ����Ͽ� ��������.

**Optional�� ����� ���� �� ������, Ŭ���̾�Ʈ�� �� ��Ȳ�� ���� ó���ؾ� �Ҷ� �������. ������� ���� ���ϰ� ���� �� �����Ƿ� ������ �ΰ��� �޼���� null�� ��ȯ����.**


### Optional with Container type


Optional�� �����̳� Ÿ�԰��� ����ϸ� �ȵȴ�. �� Optional<List<T>>�� ��ȯ�ϱ� ���ٴ� �� List<T>�� ��ȯ�ϴ� ���� Ŭ���̾�Ʈ ���忡�� Optional ó���� ���ص� �Ǳ� ������ �� ����.


### Optional with Boxing type


�ڽ̵� �⺻ Ÿ���� ���� Optional�� ������� ����. �⺻ Ÿ���� �ڽ��ϰ� �ٽ� Optional�� ���θ� ���� ���̱� �����̴�. Optional<T>�� �ƴ� OptionalInt / OptionalLong / OptionalDouble�� �������


### Optional in Collection


Optional�� �÷����� Ű / �� / ���ҷ� ����ϴ� ���� ����ġ �ʴ�. �������� ������ ȥ���� �ְ� ���� ���ɼ��� Ű�� ���̴�.
