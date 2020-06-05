package java8;

@FunctionalInterface
public interface MyFunctionalInterface {
	
	public abstract void consume();
	
	// Only one abstract method allowed in functional interface.
	//public abstract void consume2();
	
	@Override
	String toString();
	
	default void def1() {
		System.out.println("default function allowed in functional interface");
	}
}
