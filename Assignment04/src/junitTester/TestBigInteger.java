package junitTester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import orozco.BigInteger;

class TestBigInteger {

	private BigInteger num1 = new BigInteger("12345");
	private BigInteger num2 = new BigInteger("6789");

	@Test
	public void testAddition() {
		BigInteger expectedSum = new BigInteger("19134");
		BigInteger actualSum = num1.add(num2);

		assertEquals(expectedSum.toString(), actualSum.toString(), "Addition of 12345 and 6789 should equal 19134");
	}

	@Test
	public void testModulus() {
		BigInteger expectedMod = new BigInteger("5556");
		BigInteger actualMod = num1.mod(num2);

		assertEquals(expectedMod.toString(), actualMod.toString(), "Modulus of 12345 % 6789 should equal 5556");
	}

	@Test
	public void testModulusByZeroThrowsException() {
		BigInteger zero = new BigInteger("0");

		Exception exception = assertThrows(ArithmeticException.class, () -> {
			num1.mod(zero);
		});

		String expectedMessage = "Cannot divide by zero.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage), "Modulus by zero should throw ArithmeticException");
	}

	@Test
	public void testAdditionWithLargeNumbers() {
		BigInteger largeNum1 = new BigInteger("999999999999999999999");
		BigInteger largeNum2 = new BigInteger("1");

		BigInteger expectedLargeSum = new BigInteger("1000000000000000000000");
		BigInteger actualLargeSum = largeNum1.add(largeNum2);

		assertEquals(expectedLargeSum.toString(), actualLargeSum.toString(),
				"Addition of large numbers should be correct");
	}

	@Test
	public void testNegativeNumberAddition() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new BigInteger("-10000");
		});

		String expectedMessage = "Invalid input, not a valid integer.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage), "Invalid input should throw IllegalArgumentException");
	}

	@Test
	public void testInvalidBigIntegerThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new BigInteger("abc123");
		});

		String expectedMessage = "Invalid input, not a valid integer.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage), "Invalid input should throw IllegalArgumentException");
	}

}
