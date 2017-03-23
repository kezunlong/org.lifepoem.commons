package org.lifepoem.commons.lang.util;

/**
 * TwoTuple class is used to wrap two objects with generic data type.
 *  
 * @author Vincent Ke
 *
 * @param <A> first generic type 
 * @param <B> second generic type
 */
public class TwoTuple<A, B> {
	public final A first;
	public final B second;
	
	public TwoTuple(A a, B b) {
		first = a;
		second = b;
	}
	
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}
