package test.idv.cheng.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cheng.exercise08.Calculator;

public class CalculatorTest {
	Calculator cal = null;

	@Test
	public void testAdd() {
		cal = new Calculator();
		// 第一個參數是期望值，第二個參數為實際值
		Assert.assertEquals(3, (int) cal.add(1, 2));
	}

	@Test(timeout = 1000, expected = NullPointerException.class)
	public void testAdd2() {
		cal = new Calculator();
		Assert.assertEquals(2, (int) cal.add(null, 2));
	}

	@Before
	public void setUp() {
		System.out.println("before");
	}

	@After
	public void after() {
		System.out.println("after");
	}
}
