package com.baifeg.models.nav;

public class ParseException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ParseException()
	{
		super();
	}

	public ParseException(String message)
	{
		super(message);
	}

	public ParseException(String message, Throwable t)
	{
		super(message, t);
	}

	public ParseException(Throwable t)
	{
		super(t);
	}
}
