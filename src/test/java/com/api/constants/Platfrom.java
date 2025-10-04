package com.api.constants;

public enum Platfrom {
	
	FST(3),
	FRONT_DESK(2);
	
int code;
	
	private Platfrom(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}

}
