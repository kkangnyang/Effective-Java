# ITEM63. 문자열 연결은 느리니 주의하라

아이템17에서도 얘기했듯이 문자열은 불변이라서 두 문자열을 + 연산자로 연결할 경우 양쪽의 내용을 모두 복사해야하므로 성능 저하를 초래한다. 따라서 `StringBuilder` 를 사용하자

```java
public static String statement2() {
	StringBuilder b = new StringBuilder(numItems.size());
	for (int i = 0; i < numItems.size(); i++)
		b.append(numItems.get(i));
	return b.toString();
}
```