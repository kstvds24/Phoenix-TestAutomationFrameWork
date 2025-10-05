package com.api.constants;

public enum Oem {
	GOOGLE(1),
	APPLE(2);
	
	int code;
	
	private Oem(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}

}
