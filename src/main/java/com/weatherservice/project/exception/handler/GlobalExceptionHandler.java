package com.weatherservice.project.exception.handler;

import com.weatherservice.project.common.FieldErrorMessage;
import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.exception.BadRequestException;
import com.weatherservice.project.exception.CommonResponseException;
import com.weatherservice.project.exception.FieldMessageException;
import com.weatherservice.project.exception.FieldValidationException;
import com.weatherservice.project.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.ResponseMessages.FIELD_VALIDATION_ERROR_MESSAGE;
import static com.weatherservice.project.common.ResponseMessages.USER_HAS_NOT_ACCESS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        var validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                            String fieldName = ((FieldError) objectError).getField();
                            return new FieldErrorMessage(
                                    fieldName,
                                    objectError.getDefaultMessage()
                            );
                        }
                ).collect(Collectors.toList());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ResponseData<>(FIELD_VALIDATION_ERROR_MESSAGE, validationErrors));
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ResponseData<?>> handleFieldValidationException(final FieldValidationException ex) {
        FieldErrorMessage fieldValidationError = new FieldErrorMessage(ex.getField(), ex.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ResponseData<>(List.of(fieldValidationError, FIELD_VALIDATION_ERROR_MESSAGE)));
    }

    @ExceptionHandler(CommonResponseException.class)
    public ResponseEntity<?> handleCommonResponseException(final CommonResponseException ex) {
        logger.error(ex, ex);
        return ResponseData.failedResponse(ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(final AccessDeniedException ex) {
        return ResponseEntity.status(FORBIDDEN).body(new ResponseData<>(null, USER_HAS_NOT_ACCESS));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(final Exception e,
                                                    final WebRequest webRequest,
                                                    final HttpServletRequest request) {
        logger.error(e, e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ResponseData<>(null, e.getMessage()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(final ObjectNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ResponseData<>(null, ex.getMessage()));
    }

    @ExceptionHandler(FieldMessageException.class)
    public ResponseEntity<ResponseData<?>> handleFieldMessageException(final FieldMessageException ex) {
        var res = List.of(new FieldErrorMessage(ex.getField(), ex.getMessage()));
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ResponseData<>(FIELD_VALIDATION_ERROR_MESSAGE, res));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData<?>> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new ResponseData<>(ex.getMessage()));
    }
}
