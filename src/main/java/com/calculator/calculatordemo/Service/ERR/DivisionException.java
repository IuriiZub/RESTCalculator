package com.calculator.calculatordemo.Service.ERR;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Yurii on 6/7/2019.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DivisionException extends  RuntimeException{

    public DivisionException() {
        super("Second parameter coudln't be zero!");
    }

}
