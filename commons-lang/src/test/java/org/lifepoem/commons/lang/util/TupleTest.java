package org.lifepoem.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;

public class TupleTest {

	@Test
	public void createTwoTuple() {
		TwoTuple<String, Integer> tuple = Tuple.tuple("Vincent", 20);
		Assert.assertEquals(tuple.first, "Vincent");
		Assert.assertEquals(tuple.second, new Integer(20));
	}
	
	@Test
	public void createThreeTuple() {
		ThreeTuple<String, Integer, String> tuple = Tuple.tuple("Vincent", 20, "2014-6-15");
		Assert.assertEquals(tuple.first, "Vincent");
		Assert.assertEquals(tuple.second, new Integer(20));
		Assert.assertEquals(tuple.third, "2014-6-15");
	}
	
}
