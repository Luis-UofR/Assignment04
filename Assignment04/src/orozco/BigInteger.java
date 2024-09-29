package orozco;

/**
 * A BigInteger class.
 */
public class BigInteger {

	// Variable needed to store the number as a string.
	private String value;

	/**
	 * Constructor that takes a string representation of a number.
	 *
	 * @param value the string representation of the integer.
	 * @throws IllegalArgumentException if the input value is not a valid integer or
	 *                                  negative.
	 */
	public BigInteger(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid input, not a valid integer.");
		}
		this.value = value;
	}

	/**
	 * Adds two BigInteger numbers.
	 *
	 * @param other the BigInteger to add.
	 * @return a new BigInteger that represents the sum.
	 */
	public BigInteger add(BigInteger other) {
		String sum = addStrings(this.value, other.value);
		return new BigInteger(sum);
	}

	/**
	 * Returns the remainder of division between two BigInteger objects. (Always
	 * returns a non-negative BigInteger)
	 *
	 * @param other the divisor.
	 * @return a new BigInteger representing the result of the modulus.
	 * @throws ArithmeticException if the divisor is zero.
	 */
	public BigInteger mod(BigInteger other) {
		if (other.value.equals("0")) {
			throw new ArithmeticException("Cannot divide by zero.");
		}
		String remainder = modStrings(this.value, other.value);
		return new BigInteger(remainder);
	}

	/**
	 * Validates if the given string is a valid integer representation.
	 *
	 * @param value the string to validate.
	 * @return true if valid, false otherwise.
	 */
	private boolean isValid(String value) {
		if (value == null || value.isEmpty() || value.startsWith("-")) {
			return false;
		}
		for (char c : value.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds two strings representing large integers.
	 *
	 * @param num1 first integer as a string.
	 * @param num2 second integer as a string.
	 * @return the sum as a string.
	 */
	private String addStrings(String num1, String num2) {
		// Assuming both numbers are positive.
		StringBuilder sb = new StringBuilder();

		int carry = 0;
		int i = num1.length() - 1; // point to the last character
		int j = num2.length() - 1;

		/*
		 * Continues as long as there are digits left in either num1 or num2 or if there
		 * is a carry
		 */
		while (i >= 0 || j >= 0 || carry != 0) {

			// Get the current digit from num1, or 0 if we've processed all digits
			int digit1;
			if (i >= 0) {
				digit1 = num1.charAt(i) - '0'; // Convert char to int
				i--; // Move to the next digit
			} else {
				digit1 = 0; // No more digits in num1
			}

			// Get the current digit from num2, or 0 if we've processed all digits
			int digit2;
			if (j >= 0) {
				digit2 = num2.charAt(j) - '0'; // Convert char to int
				j--; // Move to the next digit
			} else {
				digit2 = 0; // No more digits in num2
			}

			int sum = digit1 + digit2 + carry;
			carry = sum / 10;
			sb.append(sum % 10);
		}

		return sb.reverse().toString();
	}

	/**
	 * Computes the modulus of two large integers represented as strings.
	 *
	 * @param num1 the dividend as a string.
	 * @param num2 the divisor as a string.
	 * @return the remainder as a string.
	 */
	private String modStrings(String num1, String num2) {

		// Use a remainder logic (assuming positive integers).
		long n1 = Long.parseLong(num1);
		long n2 = Long.parseLong(num2);

		long result = n1 % n2;
		return Long.toString(result < 0 ? result + n2 : result); // Ensure non-negative result
	}

	@Override
	public String toString() {
		return this.value;
	}
}
