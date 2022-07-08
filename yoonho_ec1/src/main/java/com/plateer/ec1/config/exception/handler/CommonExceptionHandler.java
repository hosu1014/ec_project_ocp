package com.plateer.ec1.config.exception.handler;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plateer.ec1.config.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler {
	private final MessageSourceAccessor messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder sb = new StringBuilder();
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			sb.append(String.format("%s은/는 %s.\r\n", fieldError.getField(), fieldError.getDefaultMessage()));
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
	}
	
	@SuppressWarnings("deprecation")
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> businessException(BusinessException e) {
		String message = messageSource.getMessage(e.getMessgeId());
		if(StringUtils.isEmpty(message)) message = e.getMessage();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
}
