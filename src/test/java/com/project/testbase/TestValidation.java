package com.project.testbase;

import org.testng.Assert;

public class TestValidation extends TestBase{
	
	/**
	 * This method is used for boolean Assertion. It validates if the condition Is True or not.
	 * Upon decision, accordingly message is printed to html extent report
	 * @author hingorani_a
	 * @param condition The condition that needs to be asserted 
	 * @param passMessage If assertion is true, this message will be passed to the
	 * html extent report stating step has passed
	 * @param failMessage If assertion is false, this message will be passed to the
	 * html extent report stating step failure
	 */
	public static void IsTrue(boolean condition, String passMessage, String failMessage) {
		try {
			Assert.assertTrue(condition);
			logExtentPass(passMessage); 
		}
		catch(Throwable e) {
			logExtentFail(failMessage);
			throw e;
		}
	}
	
	/**
	 * This method is used for boolean Assertion. It validates if the condition Is False or not.
	 * Upon decision, accordingly message is printed to html extent report
	 * @author hingorani_a
	 * @param condition The condition that needs to be asserted 
	 * @param passMessage If assertion is false, this message will be passed to the
	 * html extent report stating step has passed
	 * @param failMessage If assertion is true, this message will be passed to the
	 * html extent report stating step failure
	 */
	public static void IsFalse(boolean condition, String passMessage, String failMessage) {
		try {
			Assert.assertFalse(condition);
			logExtentPass(passMessage);
		}
		catch(Throwable e) {
			logExtentFail(failMessage);
			throw e;
		}
	}
	
	/**
	 * This method is used for boolean Assertion. It validates if the condition Is Equals or not.
	 * Upon decision, accordingly message is printed to html extent report
	 * @author hingorani_a
	 * @param actual The actual value you need to assert
	 * @param expected The expected value against which assertion is needed 
	 * @param passMessage If assertion is value is Equals, this message will be passed to the
	 * html extent report stating step has passed
	 * @param failMessage If assertion is value is Not Equals, this message will be passed to the
	 * html extent report stating step failure
	 */
	public static void Equals(boolean actual, boolean expected, String passMessage, String failMessage) {
		try {
			Assert.assertEquals(actual, expected);
			logExtentPass(passMessage);
		}
		catch(Throwable e) {
			logExtentFail(failMessage);
			throw e;
		}
	}
	
	/**
	 * This method is used for String Assertion. It validates if the condition Is Equals or not.
	 * Upon decision, accordingly message is printed to html extent report
	 * @author hingorani_a
	 * @param actual The actual value you need to assert
	 * @param expected The expected value against which assertion is needed 
	 * @param passMessage If assertion is value is Equals, this message will be passed to the
	 * html extent report stating step has passed
	 * @param failMessage If assertion is value is Not Equals, this message will be passed to the
	 * html extent report stating step failure
	 */
	public static void Equals(String actual, String expected, String passMessage, String failMessage) {
		try {
			Assert.assertEquals(actual, expected);
			logExtentPass(passMessage);
		}
		catch(Throwable e) {
			logExtentFail(failMessage);
			throw e;
		}
	}
	
	/**
	 * This method Fails a test with the given message.
	 * @author hingorani_a
	 * @param message This message will be passed to the html extent report stating step failure
	 */
	public static void Fail(String message) {
			Assert.fail(message);
			logExtentFail(message);
	}
	
}
