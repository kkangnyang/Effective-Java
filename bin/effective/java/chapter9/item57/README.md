# ITEM57. 지역변수의 범위를 최소화하라

### 지역변수 범위 최소화

지역변수의 유효 범위를 최소로 줄이면 코드 가독성과 유지보수성이 높아지고 오류 가능성은 낮아진다. 
 

- 지역 변수의 범위를 줄이는 가장 강력한 기법은 해당 변수를 가장 처음 사용할 때 선언하는 것이다.
- 거의 모든 지역 변수는 선언과 동시에 초기화해야한다. 
 

변수를 실제로 사용하는 시점엔 타입과 초기값이 기억나지 않을 수 있다. 


### 반복문과 지역변수

반복문은 for, for-each 형태의 반복문안에서는 반복 변수의 범위가 반복문의 몸체, 그리고 for 키워드 몸체 사이의 괄호 범위로 제한된다. 따라서 반복 변수의 값을 반복문이 종료된 뒤에도 써야 하는 상황이 아니면 while문보다는 for문을 사용하는게 낫다.


아래와 같이 i 와 i2를 선언했을 경우, i2를 반복하려는 코드 작성 시 여전시 i 변수에 대한 접근이 가능해서 아래 코드는 컴파일에 성공한다. (하지만 버그)

```java
Iterator<Element> i = c.iterator();
while(i.hasNext()) {
  doSomething(i.next());
}

Iterator<Element> i2 = c2.iterator();
while(i.hasNext()) { // 오타로 인한 버그!
  doSomethingElse(i2.next());
}
```

for문으로 바꿀 경우, 컴파일 조차 되지 않기 때문에 변수의 범위가 최소화되서 보다 안전하다.


```java
for(Iterator<Element> i = c.iterator(); i.hasNext();) {
  Element e = i.next();
}

for(Iterator<Element> i2 = c2.iterator(); i.hasNext();) { // 오류발생!!
  Element e2 = i2.next();
}
```


### 메서드를 작게 유지하고 한가지 기능에 집중하라


메서드를 작게 유지하면 한 기능과 관련된 지역변수만 사용하게된다.


 