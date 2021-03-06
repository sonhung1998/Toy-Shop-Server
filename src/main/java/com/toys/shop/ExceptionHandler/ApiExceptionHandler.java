package com.toys.shop.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.toys.shop.Entities.ErrorMessage;

@RestControllerAdvice

public class ApiExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public static ErrorMessage handlerAllException(Exception ex, WebRequest request) {
		return new ErrorMessage(10000, "Có lỗi xảy ra: " + ex.getLocalizedMessage());
	}

}
