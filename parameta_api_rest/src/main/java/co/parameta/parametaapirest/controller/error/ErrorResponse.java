package co.parameta.parametaapirest.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorResponse holds information about an error or exception
 * to be handled by ExceptionHandlerController.
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String param;
    private String message;


    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
