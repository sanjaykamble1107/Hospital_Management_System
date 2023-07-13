package com.hms.exception.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hms.exception.model.AffiliatedWithNotFoundException;
import com.hms.exception.model.AppointmentNotFoundException;
import com.hms.exception.model.DepartmentNotFoundException;
import com.hms.exception.model.NurseNotFoundException;
import com.hms.exception.model.PatientNotFoundException;
import com.hms.exception.model.PhysicianNotFoundException;
import com.hms.exception.model.ProcedureNotFoundException;
import com.hms.exception.model.TrainedInNotFoundException;
import com.hms.exception.model.ValidationException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ExceptionResponse> handler(ValidationException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionResponse> methodArgumentNotValidHandler(
			MethodArgumentNotValidException ex) {

		List<String> validation = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			validation.add(error.getDefaultMessage());
		}

		ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(LocalDateTime.now(),
				"Validation Failed", validation.toString());
		ResponseEntity<ValidationExceptionResponse> response = new ResponseEntity<ValidationExceptionResponse>(
				exceptionResponse, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> violation(ConstraintViolationException constraintViolationException){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
				"Validation Failed");
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(
				exceptionResponse, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(AffiliatedWithNotFoundException.class)
	public ResponseEntity<ExceptionResponse> AffiliatedWithNotFoundExceptionHandler(
			AffiliatedWithNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> AppointmentNotFoundExceptionHandler(AppointmentNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> DepartmentNotFoundExceptionHandler(DepartmentNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(NurseNotFoundException.class)
	public ResponseEntity<ExceptionResponse> NurseNotFoundExceptionHandler(NurseNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(PhysicianNotFoundException.class)
	public ResponseEntity<ExceptionResponse> PhysicianNotFoundExceptionHandler(PhysicianNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ExceptionResponse> PatientNotFoundExceptionHandler(PatientNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ProcedureNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ProcedureNotFoundExceptionHandler(ProcedureNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(TrainedInNotFoundException.class)
	public ResponseEntity<ExceptionResponse> TrainedInNotFoundExceptionHandler(TrainedInNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exceptionResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

}
