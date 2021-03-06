package com.order.item.manage.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomeEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		Map<String,Object> errorMap=new LinkedHashMap<>();
		errorMap.put("stamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a")));
		errorMap.put("status", status);
		List<String> errors=ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(m->m.getDefaultMessage())
				.collect(Collectors.toList());
				errorMap.put("Error List", errors);
				
				return new ResponseEntity<Object>(errorMap,status);
	}
	/**
	 * Exception handler method to handle OrderItemNotFound
	 * 
	 * */
	@ExceptionHandler(OrderItemNotFound.class)
	public ResponseEntity<Map<String,Object>> handleOrderItemNotFound(OrderItemNotFound ex){
		Map<String,Object> errorMap=new LinkedHashMap<>();
		errorMap.put("stamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a")));
		errorMap.put("status", HttpStatus.NOT_FOUND);
		errorMap.put("error", ex.getMessage());
		return new ResponseEntity<Map<String,Object>>(errorMap, HttpStatus.NOT_FOUND);
	}
}
