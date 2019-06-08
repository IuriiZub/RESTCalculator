package com.calculator.calculatordemo.controller;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;

import com.calculator.calculatordemo.Service.ERR.DivisionException;
import com.calculator.calculatordemo.Service.ERR.ErrorData;
import com.calculator.calculatordemo.Service.calculator.*;
import com.calculator.calculatordemo.model.CalculationResult;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;


/**
 * Created by Yurii on 6/7/2019.
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class CalculatorController {

    final HttpHeaders httpHeaders = new HttpHeaders();
    AnnotationConfigApplicationContext applicationContext;
    Calculator calculator;


    @RequestMapping(value = "/plus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> addData(@Valid @RequestBody CalculationDataDTO inputData) {
        applicationContext =
                new AnnotationConfigApplicationContext("com.calculator.calculatordemo");
        calculator = applicationContext.getBean(CalculatorAddictor.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/minus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> deductData(@Valid @RequestBody CalculationDataDTO inputData) throws DivisionException {
        applicationContext =
                new AnnotationConfigApplicationContext("com.calculator.calculatordemo");
        calculator = applicationContext.getBean(CalculatorDeductor.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> multiplyData(@Valid @RequestBody CalculationDataDTO inputData) throws DivisionException {
        applicationContext =
                new AnnotationConfigApplicationContext("com.calculator.calculatordemo");
        calculator = applicationContext.getBean(CalculatorMultipliyer.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> divideData(@Valid @RequestBody CalculationDataDTO inputData) {
        applicationContext =
                new AnnotationConfigApplicationContext("com.calculator.calculatordemo");
        calculator = applicationContext.getBean(CalculatorDivider.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorData> handleUserNotFoundException(DivisionException ex, WebRequest request) {
        ErrorData errorDetails = new ErrorData(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleException(HttpMessageNotReadableException e, WebRequest request) {
        e.printStackTrace();
        ErrorData error = new ErrorData(e.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


}

