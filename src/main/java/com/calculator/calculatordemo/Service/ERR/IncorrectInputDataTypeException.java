package com.calculator.calculatordemo.Service.ERR;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectInputDataTypeException extends Exception {

    IncorrectInputDataTypeException(String message) {
        super("Invalid value of " + message);
    }
}
