package com.javaexpress.exception;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

	private List<ErrorAPI> errorAPI;	
	
}
