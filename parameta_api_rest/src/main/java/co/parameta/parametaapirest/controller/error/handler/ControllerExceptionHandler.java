package co.parameta.parametaapirest.controller.error.handler;

import co.parameta.parametaapirest.controller.error.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.parameta.parametaapirest.constant.ErrorConstant.PARAMETER_TYPE_MISMATCH;
import static co.parameta.parametaapirest.constant.ErrorConstant.CONSTRAINT_VIOLATION;
import static co.parameta.parametaapirest.constant.ErrorConstant.INTERNAL_SERVER_ERROR;
import static co.parameta.parametaapirest.constant.ErrorConstant.INVALID_DATE_FORMAT;
import static co.parameta.parametaapirest.constant.ErrorConstant.METHOD_ARGUMENT_NOT_VALID;
import static co.parameta.parametaapirest.constant.ErrorConstant.METHOD_NOT_SUPPORTED;
import static co.parameta.parametaapirest.constant.ErrorConstant.MISSING_REQUEST_PARAMETER;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodValidationException(HandlerMethodValidationException ex, Locale locale) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<ErrorResponse> errors = new ArrayList<>();

        List<ParameterValidationResult> allValidationResults = ex.getAllValidationResults();

        try {
            errors = allValidationResults.stream()
                    .flatMap(validationResult -> validationResult.getResolvableErrors().stream())
                    .map(messageSourceResolvable -> {
                        String defaultMessage = messageSourceResolvable.getDefaultMessage();
                        String message = messageSource.getMessage(defaultMessage, null, defaultMessage, locale);
                        String arguments = formatArguments(messageSourceResolvable.getArguments());
                        return new ErrorResponse(defaultMessage, arguments, message);
                    })
                    .distinct()
                    .collect(Collectors.toList());
        } catch (Exception ignored) {
            // TODO: log the exception
        }

        return new ResponseEntity<>(errors, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<ErrorResponse> errors = new ArrayList<>();

        try {
            errors = ex.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(objectError -> {
                        String message = messageSource.getMessage(objectError.getDefaultMessage(), null, METHOD_ARGUMENT_NOT_VALID, locale);
                        return new ErrorResponse(METHOD_ARGUMENT_NOT_VALID, message);
                    })
                    .distinct()
                    .collect(Collectors.toList());

        } catch (Exception ignored) {
            // TODO: log the exception
        }

        return new ResponseEntity<>(errors, httpStatus);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex, Locale locale) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<ErrorResponse> errors = new ArrayList<>();

        try {
            String messageCode = PARAMETER_TYPE_MISMATCH;

            Class<?> requiredType = ex.getRequiredType();
            if (Objects.requireNonNull(requiredType).isAssignableFrom(LocalDate.class)) {
                messageCode = INVALID_DATE_FORMAT;
            }

            String message = messageSource.getMessage(messageCode, null, messageCode, locale);
            errors = List.of(new ErrorResponse(messageCode, ex.getName(), message));

        } catch (Exception ignored) {
            // TODO: log the exception
        }

        return new ResponseEntity<>(errors, httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorResponse>> handleConstraintViolation(ConstraintViolationException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<ErrorResponse> errors = ex.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new ErrorResponse(CONSTRAINT_VIOLATION, constraintViolation.getMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, httpStatus);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<List<ErrorResponse>> handleMissingRequestParameter(MissingServletRequestParameterException ex, Locale locale) {
        String message = messageSource.getMessage(MISSING_REQUEST_PARAMETER, null, MISSING_REQUEST_PARAMETER, locale);
        String parameterName = ex.getParameterName();
        List<ErrorResponse> errors = List.of(new ErrorResponse(MISSING_REQUEST_PARAMETER, parameterName, message));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<List<ErrorResponse>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        List<ErrorResponse> errors = List.of(new ErrorResponse(METHOD_NOT_SUPPORTED, ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<List<ErrorResponse>> handleAll(Exception ex, Locale locale) {
        String message = messageSource.getMessage(INTERNAL_SERVER_ERROR, null, INTERNAL_SERVER_ERROR, locale);
        List<ErrorResponse> error = List.of(new ErrorResponse(INTERNAL_SERVER_ERROR, message));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String formatArguments(Object[] arguments) {
        if (arguments != null) {
            return Stream.of(arguments)
                    .filter(DefaultMessageSourceResolvable.class::isInstance)
                    .map(o -> ((DefaultMessageSourceResolvable) o).getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }
}
