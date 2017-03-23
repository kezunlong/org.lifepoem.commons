package org.lifepoem.commons.lang.util;

/**
 * FiveTuple class is used to wrap five objects with generic data type.
 * 
 * @author Vincent Ke
 *
 * @param <A> first generic type 
 * @param <B> second generic type
 * @param <C> third generic type
 * @param <D> fourth generic type
 * @param <E> fifth generic type
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
	public final E fifth;
	
	public FiveTuple(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		
		fifth = e;
	}
	
	public String toString() {
		return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
	}
}
