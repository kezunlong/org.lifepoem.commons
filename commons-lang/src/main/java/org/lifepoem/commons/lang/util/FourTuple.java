package org.lifepoem.commons.lang.util;

/**
 * FourTuple class is used to wrap four objects with generic data type.
 * 
 * @author Vincent Ke
 *
 * @param <A> first generic type 
 * @param <B> second generic type
 * @param <C> third generic type
 * @param <D> fourth generic type
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
	public final D fourth;
	
	public FourTuple(A a, B b, C c, D d) {
		super(a, b, c);
		
		fourth = d;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ", " + fourth + ")";
	}
}
