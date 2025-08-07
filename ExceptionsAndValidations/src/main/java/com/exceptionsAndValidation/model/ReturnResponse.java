package com.exceptionsAndValidation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnResponse
{
	private Boolean success;
	private String message;
	private Object entity;
	private String errorDetails;
	
	public void setExceptionDetails(String message, Exception e)
	{
		this.success = false;
		this.message = message;
		this.errorDetails = e.getMessage();
	}
	
	public void setExceptionDetails(String message)
	{
		this.success = false;
		this.message = message;
//		this.errorDetails = e.getMessage();
	}

	public void setEntityResponse(String message, Object object)
	{
			this.success=true;
			this.entity = object;
			this.message = message;
	}
}
