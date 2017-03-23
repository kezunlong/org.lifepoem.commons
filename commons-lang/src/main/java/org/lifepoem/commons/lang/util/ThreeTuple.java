package org.lifepoem.commons.lang.util;

/**
 * ThreeTuple class is used to wrap three objects with generic data type.
 * 
 * @author Vincent Ke
 *
 * @param <A> first generic type 
 * @param <B> second generic type
 * @param <C> third generic type
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	public final C third;
	
	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		
		third = c;
	}
	
	public String toString() {
		return "(" + first + ", " + second + ", " + third + ")";
	}
}
