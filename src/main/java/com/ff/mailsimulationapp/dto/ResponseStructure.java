package com.ff.mailsimulationapp.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> { 
	
	int statusCode;
	
	String message;
	
	T data;

}
