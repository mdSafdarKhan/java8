package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Person {
	default void method1() {
		System.out.println("default method in interface");
	}

	// C.E
//	default final void sayHello1(){
//		System.out.println("final keyword is not allowed in default method");
//	}

	// C.E
//	default synchronized void sayHello2(){
//		System.out.println("synchronized keyword is not allowed in default method");
//	}

	// C.E
//	default String toString(){ 
//		System.out.println("Object methods cannot be overridden in default method");
//	}	
}

public class Test implements Person {

	public static void main(String[] args) {
		Test t = new Test();
		t.method1();

		List<EmployeeNameAndAge> list = new ArrayList<>();
		EmployeeNameAndAge emp1 = new EmployeeNameAndAge();
		emp1.setName("Yousuf");
		emp1.setAge(100);
		EmployeeNameAndAge emp12 = new EmployeeNameAndAge();
		emp12.setName("Ahmed");
		emp12.setAge(12);
		list.add(emp1);
		list.add(emp12);
		List<EmployeeName> list2 = list.stream().filter(emp -> emp.getAge() > 50)
				.map(emp -> new EmployeeName(emp.getName())).collect(Collectors.toList());
		System.out.println(list2);

		MyFunctionalInterface fnc1 = () -> System.out.println("consume method cnsumed...");
		fnc1.consume();

		// Java Built-In Lambda Interfaces

		Consumer<String> consumer = (s) -> System.out.println(s.toUpperCase());
		consumer.accept("safdar");

		Predicate<Integer> predict = (n) -> n > 20;
		System.out.println(predict.test(1));

		Function<String, Integer> function = (s) -> Integer.parseInt(s);
		System.out.println(function.apply("101"));

		Supplier<String> supplier = () -> UUID.randomUUID().toString();
		System.out.println(supplier.get());

		// Method reference
		// Reference to a static method of class.
		Function<String, Integer> staticMethodRef = Integer::parseInt;
		System.out.println(staticMethodRef.apply("200"));

		// Reference to an instance method of class.
		// Test test = new Test();

		// Method reference to instance method of non-existing object
		// Employee::getName

		// Reference to a constructor of a class.
		// Employee::new

		// Stream
		// Getting Stream From a Collection using the stream() method;
		list.stream().forEach(emp -> System.out.println(emp.getName()));

		// Getting Stream Using Stream.of(T[]) Static Factory Method
		Stream<Integer> ints = Stream.of(new Integer[] { 10, 20, 30 });
		ints.forEach(i -> System.out.println(i));

		// Intermediate operation filter(), map() etc.
		Stream<EmployeeName> list23 = 
				list.stream().filter(emp -> emp.getAge() > 50).map(emp -> new EmployeeName(emp.getName()));
		System.out.println(list23);

		// Terminal Operation forEach(), collect() etc.
		List<EmployeeName> list233 = 
				list.stream().filter(emp -> emp.getAge() > 50).map(emp -> new EmployeeName(emp.getName())).collect(Collectors.toList());
		System.out.println(list233);
		
		/*Reductions
		 	1. Reductions are terminal operation which refers to the process of combining all elements in the 
		 		stream repeatedly to produce a single value which is returned as the result of the reduction operation.
		 	
		 	2. They trigger the processing of data ;They are always present at the end of a chain of Stream methods.
			
			3. Stream classes like IntStream has built-in methods like average(), count(), sum() to perform 
				reduce operations and mapToLong(), mapToDouble() methods for transformations.	
		 */
		List<Integer> int2s = Arrays.asList(10,20,30);
		Integer sum = int2s.stream().reduce(0, (num1,num2) -> num1+num2);
		System.out.println(sum);
		
		/*
		1st argument :
			The identity is the initial value of the type T which will be used as the first value in the reduction operation
		
		2nd argument: Reduction operation of, type BinaryOpertor<T>
		*/
		
		//Built-In Reduction Method
		//min(), max(), Count(), Sum()
		
		//Boolean Reduction
		//allMatch(), noMatch(), anyMatch()
		
		//Finding element
		//findFirst(), findAny()
		
		//Counting Element
		// count()
		
		//Collectors
		
		//Collector is nothing but a mutable reduction operation that accumulates elements from the stream into a 
		//	mutable container like ArrayList , HashSet and Map etc.
		
		//Collecting in a List
		List<Integer> smallerThan10 = int2s.stream().filter(n -> n > 10).collect(Collectors.toList());
		System.out.println(smallerThan10);
		
		//Collecting in Map
		Map<String, Integer> map = list.stream().collect(Collectors.toMap(EmployeeNameAndAge::getName, EmployeeNameAndAge::getAge));
		System.out.println(map);
		
		//Collecting in String
		String s = list.stream().map(EmployeeNameAndAge::getName).collect(Collectors.joining(","));
		System.out.println(s);
	}
}
