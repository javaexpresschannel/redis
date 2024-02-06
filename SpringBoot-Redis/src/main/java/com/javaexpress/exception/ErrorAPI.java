package com.javaexpress.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data // setter getter tostring
@Builder
public class ErrorAPI {

	private Integer statusCode; // 415 400 4...
	private String title; // server error client error validation error 
	private String status ; // BAD REQUEST unsupported media type 
	private String details; // real error message like product not found in db 
	private LocalDateTime localDateTime;
}
