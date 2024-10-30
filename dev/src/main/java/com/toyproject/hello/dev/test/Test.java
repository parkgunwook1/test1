interface LamdaInterface {
	public int calc(int x, int y);
}

public class Test {

	public static void main(String[] args) {

		Test test = new Test();
		test.LamdaTest();

		test.mappingStream();
	}

	public void LamdaTest() {

		/*
		 * 람다식
		 * 
		 * Java 8부터 지원하는 함수 구현과 호출 만으로 프로그래밍 하는 함수형 프로그램이 방식 지원 - 이름이 없는 익명함수 - 함수적
		 * 인터페이스로 구현 - 코틀린 같은 언어도 객체지향 + 함수형 언어
		 * 
		 * 람다식 형식 - 타입 생략 가능 - 매개변수가 한개라면 괄호도 생략 가능 - 매개변수가 없는 경우는 괄호 생략 불가 - 실행문이 한 개라면
		 * 중괄호도 생략 가능
		 */
		LamdaInterface ide1 = (int x, int y) -> x + y;
		LamdaInterface ide2 = (int x, int y) -> x - y;
		LamdaInterface ide3 = (int x, int y) -> x * y;
		LamdaInterface ide4 = (int x, int y) -> x / y;

		System.out.println(ide1.calc(5, 10));
		System.out.println(ide2.calc(5, 10));
		System.out.println(ide3.calc(5, 10));
		System.out.println(ide4.calc(5, 10));
	}

	public void StreamTest() {
		/*
		 * Stream
		 * 
		 * java 8부터 추가된 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자이다. 배열이나 컬렉션(List,
		 * Set, Map)으로 원하는 값을 얻을 때 for문 과도한 사용을 방지하기 위해 나온 개념이다.
		 * 
		 * 스트림은 선언, 가공, 반환 세 부분으로 구성한다. - 선언 : 스트림 인스턴스 생성, 배열, 컬렉션(list, set, map) 등을
		 * 스트림 형태로 만들기 - 가공 : 스트림을 필요한 형태로 가공, 필터링, 매핑 등 중간 작업 - 반환 : 가공한 값을 원하는 형태로
		 * 가져오기
		 * 
		 * 장점 - 사용하기 편함, 코드 간결해짐 - 가독성 좋아진다.
		 * 
		 * 단점 - 디버그 힘듬(한번에 수행되기 때문) - 재활용 불가능(스트림은 사용후 close)
		 * 
		 * 외부 반복자와 내부 반복자 - 일반적인 반복 : for, while - 외부반복자 : iterator -> next() - 내부반복자 :
		 * forEach -> 람다식
		 * 
		 * 순차 스트림 vs 병렬 스트림
		 * 
		 * 순차 스트림 : 하나의 쓰레드에서 모든 반복을 수행하는 것을 의미한다. 싱글 스레드를 사용하기 때문에 CPU 코어 자원을 마음껏 활용하지
		 * 못하는 대신, 공유 자원 이슈를 고민할 필요가 없다.
		 * 
		 * 병렬 스트림 : 여러 개의 쓰레드에서 반복을 나누어 수행하는 것이다. 멀티쓰레드이므로 공유자원 동기화 문제가 생긴다.
		 * 
		 * 병렬 스트림은 그 자체로 순차적일 수밖에 없는 for-loop보다는 확실히 빠르다.
		 * 
		 */

		// Arrays.asList -> 배열을 리스트로 변환하는 데 사용
		// Arrays.stream -> 배열을 스트림으로 변환하는 데 사용

		// filter -> 필터링
		// map -> 매핑
		// 집계 -> sum, average

		// 문자열 배열을 List로 변환
		List<String> list = Arrays.asList("a", "b", "c", "d", "e");
		// List 객체를 stream() 메서드를 이용해 Stream 객체로 생성
		Stream<String> stream = list.stream();
		// 내부 반복자를 이용해 출력
		stream.forEach(s -> System.out.println(s));

		int[] numbers = { 1, 2, 3, 4, 5 };

		// 배열을 스트림으로 변환
		int sum = Arrays.stream(numbers).filter(num -> num % 2 == 0) // 짝수 필터링
				.sum(); // 합계

		// Stream.builder()
		Stream<String> builderStream = Stream.<String>builder().add("a").add("b").add("c").build();

		// 람다식 Stream.generate(), iterate()
		// 생성할 때 스트림의 크기가 정해져있지 않기(무한하기)때문에 최대 크기를 제한해줘야 한다.
		Stream<String> generatedStream = Stream.generate(() -> "a").limit(3);

		Stream<Integer> iteratedStream = Stream.iterate(0, n -> n + 2).limit(5); // 0,2,4,6,8

		// 기본 타입형 스트림
		IntStream intStream = IntStream.range(1, 5); // [1,2,3,4]

		// 병렬 스트림 parallelStream
		Stream<String> parallelStream = list.parallelStream();

	}
	
	public void processStream() {
		
		/* 스트림 가공 
		 * 
		 * 중간 처리 작업
		 * 	- 필터링(Filtering),매핑(Mapping), 정렬(Sorting), 그룹핑(Groupping)
		 * 
		 * 필터링 
		 * 		- 조건 처리
		 * 		- distinct() : 중복 제거 된 스트림 생성
		 * 		- filter() : true 리턴 데이터만 스트링 생성
		 * */
		
		// List 객체 생성
		List<String> list = Arrays.asList("홍길동", "홍길동", "김유신", " 이순신" , "유관순");
		
		// distinct() 메서드로 중복 제거 후 내부 반복자로 출력
		list.stream().distinct().forEach(n -> System.out.println(n));
		
		// 홍으로 시작하는 문자열로 필터링 후 내부 반복자로 출력
		list.stream().filter(n -> n.startsWith("홍"))
					 .forEach(n -> System.out.println(n));
		
		// 중복제거 후 "홍"으로 시작하는 문자열 내부 반복자로 출력
		list.stream().distinct().filter(n -> n.startsWith("홍")).forEach(n -> System.out.println(n));
	}
	
	
	public void mappingStream() {
		// Mapping : 데이터 변환 처리
		// 스트림 내 요소들을 하나씩 특정 값으로 변환하는 작업, 값을 변환하기 위한 람다를 인자로 받는다.
		// 스트림을 원하는 모양의 새로운 스트림으로 변환하고싶을 때 사용
		
		List<String> list = Arrays.asList("111 222", "333 444", "555 666");
		list.stream().flatMap(data -> Arrays.stream(data.split(" ")))
			         .forEach(word -> System.out.println(word));
		
		// 문자열을 ,로 분리해서 double 자료형으로 변환해서 매핑
		List<String> list2 = Arrays.asList("1.1 , 2.2 , 3.3 , 4.4 , 5.5 , 6.6");
		DoubleStream dsr = list2.stream().flatMapToDouble(data -> {
			String[] strArr = data.split(",");
			double[] dArr = new double[strArr.length];
			for(int i = 0; i<dArr.length; i++) {
				dArr[i] = Double.parseDouble(strArr[i].trim());
			}
			return Arrays.stream(dArr);
		});
		dsr.forEach(n -> System.out.println(n));
	}
	List<String> list = Arrays.asList("1","2");
	Stream<String> stream1 = list.stream().map(String::toUpperCase);
	// 스트림에 있는 값을 원하는 메소드에 입력값으로 넣으면 메소드 실행 결과(반환 값)가 담긴다.

	/* ::
	 * java에서 ::는 메서드 참조 연산자이다. 메서드 참조는 람다 표현식을 간결하게 표현하는 방법으로, 특정 메서드를 바로 참조하여 사용할 때 쓰인다.
	 * 
	 * 메서드 참조의 기본 구조
	 * 		- 메서드 참조는 보통 클래스이름::메서드이름 또는 객체이름::메서드이름 형태로 사용한다.
	 * 		- 람다 표현식과 달리 메서드를 직접 참조하므로 가독성이 높아지고 코드가 간결해지는 장점이 있다.
	 *  */
	
}