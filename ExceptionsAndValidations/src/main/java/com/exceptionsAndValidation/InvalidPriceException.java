package com.exceptionsAndValidation;

public class InvalidPriceException extends RuntimeException
{
	public InvalidPriceException(String errorMsg)
	{
		super(errorMsg);
	}
}
