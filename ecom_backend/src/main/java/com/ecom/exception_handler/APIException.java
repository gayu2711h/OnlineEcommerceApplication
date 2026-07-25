package com.ecom.exception_handler;

public class APIException extends RuntimeException 
{
	public APIException(String msg)
	{
		super(msg);
	}
}
