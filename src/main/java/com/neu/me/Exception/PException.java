package com.neu.me.Exception;

public class PException extends Exception{

	
	public PException(String message)
	{
		super(message);
	}
	
	public PException(String message, Throwable cause)
	{
		super(message,cause);
	}

}
