package org.lifepoem.commons.lang.util;

/**
 * 使用类型推断和Static方法，简化元组类型的使用
 * 比如：
 * 	TwoTuple<String, Integer> ttsi = Tuple.tuple("hi", 47);
 * 在使用静态导入import static com.lifepoem.util.Tuple.*;后，可以进一步简化为
 * 	TwoTuple<String, Integer> ttsi = tuple("hi", 47);
 * @author Vincent Ke
 *
 */
public class Tuple {
	public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}
	
	public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}
	
	public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
		return new FourTuple<A, B, C, D>(a, b, c, d);
	}
	
	public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
		return new FiveTuple<A, B, C, D, E>(a, b, c, d, e);
	}
}